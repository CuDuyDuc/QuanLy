package com.example.quanly.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.adapter.OrderOuterAdapter;
import com.example.quanly.dao.StatisticDAO;
import com.example.quanly.model.OrderOuter;
import com.example.quanly.model.ThongKe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class StatisticForDateFragment extends Fragment {

    private TextView txtdate;
    private TextView txttotalprice;
    private TextView txtcountorder;
    private TextView txtcountservice;
    private RecyclerView rcv;
    private String choiceDate;
    private Calendar calendar;
    private StatisticDAO statisticDAO;
    private OrderOuterAdapter orderOuterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistic_by_day, container, false);
        txtdate = v.findViewById(R.id.statictisForDay_date);
        txttotalprice = v.findViewById(R.id.statictisForDay_totalprice);
        txtcountorder = v.findViewById(R.id.statictisForDay_countorder);
        txtcountservice = v.findViewById(R.id.statictisForDay_countService);
        calendar = Calendar.getInstance();

        statisticDAO = new StatisticDAO(getContext());
        rcv = v.findViewById(com.example.quanly.R.id.statictisForDay_recycleview);
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
        choiceDate = year +"-"+(month + 1)+"-"+day;
        txtdate.setText(choiceDate);
        txtdate.setOnClickListener(v->
                setDatepicker());
    }

    private void setDatepicker(){
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get( Calendar.YEAR);

        DatePickerDialog datePicker = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
        datePicker.show();
    }

    private Calendar selectedDate;
    private final DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
        selectedDate = Calendar.getInstance();
        selectedDate.set(Calendar.YEAR, year);
        selectedDate.set(Calendar.MONTH, month);
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        // Cập nhật hiển thị trên TextView hoặc thực hiện các thao tác khác
        updateSelectedDateTextView();
    };

    private void updateSelectedDateTextView() {
        if (selectedDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = dateFormat.format(selectedDate.getTime());
            setData(formattedDate);
            txtdate.setText(formattedDate);
        }
    }

    private void setData(String date){
        statisticDAO.getForDate(date, new StatisticDAO.StatisticDAOITF() {
            @Override
            public void xuli(Object obj) {
                orderOuterAdapter = new OrderOuterAdapter((ArrayList<OrderOuter>) obj, getContext());
                rcv.setAdapter(orderOuterAdapter);
            }
        });
        statisticDAO.getThongKe(date, new StatisticDAO.StatisticDAOITF() {
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