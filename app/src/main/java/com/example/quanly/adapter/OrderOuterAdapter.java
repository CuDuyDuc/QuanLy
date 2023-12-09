package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.dao.OrderDAO;
import com.example.quanly.model.OrderInner;
import com.example.quanly.model.OrderOuter;


import java.util.ArrayList;

public class OrderOuterAdapter extends RecyclerView.Adapter<OrderOuterAdapter.ViewHolder> {
    private ArrayList<OrderOuter> arrayList;
    private Context context;
    private OrderInnerAdapter orderInnerAdapter;
    private OrderDAO orderDAO;

    public OrderOuterAdapter(ArrayList<OrderOuter> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        orderDAO = new OrderDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(com.example.quanly.R.layout.item_comfirm_serrviceouter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderOuter hoaDonouter = arrayList.get(position);
        holder.idorder.setText("#"+hoaDonouter.getIddonhang());
        holder.dateorder.setText(hoaDonouter.getDate());

        setDataInner(holder.totalprice, hoaDonouter.getIddonhang(), holder.rcv, 15000);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idorder, dateorder, totalprice;
        private RecyclerView rcv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idorder = itemView.findViewById(R.id.itemorder_outer_id);
            dateorder = itemView.findViewById(R.id.itemorder_outer_date);
            totalprice = itemView.findViewById(R.id.itemorder_outer_price);
            rcv = itemView.findViewById(R.id.itemorder_outer_rcv);
        }
    }
    private void changedStatus(String idorder, TextView txt){
        orderDAO.changeStatusToDelivery(idorder, new OrderDAO.OrderDAOITF() {
            @Override
            public void xuli(Object obj) {
                txt.setText("Đã xác nhận");
            }
        });
    }

    private void setDataInner(TextView price, String id , RecyclerView rcv, int ship){
        orderDAO.getDonHangInner(id, new OrderDAO.OrderDAOITF() {
            @Override
            public void xuli(Object obj) {
                int tongtienhang = 0;
                ArrayList<OrderInner> arr = (ArrayList<OrderInner>) obj;
                for (int i = 0; i < arr.size() ; i++) {
                    tongtienhang += (arr.get(i).getPrice() * arr.get(i).getQuantitty());
                }
                price.setText("Tổng tiền: "+(tongtienhang + ship) + " VNĐ");
                orderInnerAdapter = new OrderInnerAdapter(arr, context);
                LinearLayoutManager l = new LinearLayoutManager(context);
                l.setOrientation(LinearLayoutManager.VERTICAL);
                rcv.setLayoutManager(l);
                rcv.setAdapter(orderInnerAdapter);
                orderInnerAdapter.notifyDataSetChanged();
            }
        });
    }
}
