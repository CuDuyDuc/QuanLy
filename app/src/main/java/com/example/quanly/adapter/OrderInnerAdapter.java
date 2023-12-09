package com.example.quanly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanly.R;
import com.example.quanly.model.OrderInner;


import java.util.ArrayList;

public class OrderInnerAdapter extends RecyclerView.Adapter<OrderInnerAdapter.ViewHolder> {
    private ArrayList<OrderInner> arrayList;
    private Context context;

    public OrderInnerAdapter(ArrayList<OrderInner> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(com.example.quanly.R.layout.item_service_inner, parent,  false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderInner donhang = arrayList.get(position);
        int idimg = context.getResources().getIdentifier("drawable/"+donhang.getImg(), null, context.getPackageName());
        holder.img.setImageResource(idimg);
        holder.price.setText((donhang.getPrice()) * donhang.getQuantitty()+ "VNĐ");
        holder.quantity.setText("SL: "+donhang.getQuantitty()+"");
        holder.description.setText(donhang.getDescription());
        holder.name.setText(donhang.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description, quantity, price;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemdichvu_name);
            description = itemView.findViewById(R.id.itemdichvu_mota);
            quantity = itemView.findViewById(R.id.itemdichvu_quantity);
            price = itemView.findViewById(R.id.itemdichvu_price);
            img = itemView.findViewById(R.id.itemdichvu__img);
        }
    }

    public int getTotalPrice(){
        int total = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            total += (arrayList.get(i).getPrice() * arrayList.get(i).getQuantitty());
        }
        return total;
    }
}
