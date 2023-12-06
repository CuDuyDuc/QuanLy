package com.example.quanly.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.adapter.OrderOuterAdapter;
import com.example.quanly.dao.StatisticDAO;
import com.example.quanly.model.OrderOuter;
import com.example.quanly.model.ThongKe;


import java.util.ArrayList;
import java.util.Calendar;


public class StatisticForDateToDateFragment extends Fragment {

    private TextView txtfromdate, txttodate;
    private TextView txttotalprice;
    private TextView txtcountorder;
    private TextView txtcountservice;
    private RecyclerView rcv;
    private String choiceDate;
    private Calendar calendar;
    private StatisticDAO statisticDAO;
    private OrderOuterAdapter orderOuterAdapter;
    int year1 , month1, day1, day2, month2, year2;
    private String fromdate , todate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(com.example.quanly.R.layout.fragment_statistic_day_to_day, container, false);
        txtfromdate = v.findViewById(R.id.statictisdatetodate_fromdate);
        txttodate = v.findViewById(R.id.statictisdatetodate_todate);
        txttotalprice = v.findViewById(R.id.statictisdatetodate_totalprice);
        txtcountorder = v.findViewById(R.id.statictisdatetodate_countorder);
        txtcountservice = v.findViewById(R.id.statictisdatetodate_countservice);
        calendar = Calendar.getInstance();
        statisticDAO = new StatisticDAO(getContext());
        rcv = v.findViewById(R.id.statictisdatetodate_rcv);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(l);
        setListener();



        return v;
    }

    private void setListener(){
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get( Calendar.YEAR);
        day1 = day;
        day2 = day;
        month1 = month;
        month2 = month;
        year1 = year;
        year2 = year;
        choiceDate = year +"-"+(month + 1)+"-"+day;
        fromdate = choiceDate;
        todate = choiceDate;
        txtfromdate.setText(choiceDate);
        txttodate.setText(choiceDate);
        txttodate.setOnClickListener(v->
                showDatePicker2(txttodate));
        txtfromdate.setOnClickListener(v -> showDatePicker1(txtfromdate));
    }

    private void showDatePicker1(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                fromdate = i +"-" +(i1 + 1) +"-" + i2;
                textView.setText(fromdate);
                day1 = i2;
                month1 = i1;
                year1 = i;
                setData(fromdate ,todate);

            }
        },year1,month1,day1);

        datePickerDialog.show();

    }

    private void showDatePicker2(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                todate = i +"-" +(i1 + 1) +"-" + i2;
                textView.setText(todate);
                day2 = i2;
                month2 = i1;
                year2 = i;
                setData(fromdate ,todate);

            }
        },year2,month2,day2);
        datePickerDialog.show();
        }



    private void setData(String fromdate, String todate){
        statisticDAO.getDaytoDay(fromdate, todate, new StatisticDAO.StatisticDAOITF() {
            @Override
            public void xuli(Object obj) {
                orderOuterAdapter = new OrderOuterAdapter((ArrayList<OrderOuter>) obj, getContext());
                rcv.setAdapter(orderOuterAdapter);
            }
        });
        statisticDAO.getThongKe(fromdate, todate, new StatisticDAO.StatisticDAOITF() {
            @Override
            public void xuli(Object obj) {
                ThongKe thongKe = (ThongKe) obj;
                if (thongKe.getDoanhthu() == "null") {
                    txttotalprice.setText("0 đ");
                }else {
                    txttotalprice.setText(thongKe.getDoanhthu()+" đ");
                }
                txtcountorder.setText(thongKe.getHoadon());
                txtcountservice.setText(thongKe.getDichvu());
            }
        });
    }
}