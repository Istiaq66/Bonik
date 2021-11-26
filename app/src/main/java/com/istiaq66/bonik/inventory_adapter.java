package com.istiaq66.bonik;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class inventory_adapter extends RecyclerView.Adapter<inventory_adapter.MYViewHolder>{

    private List<inventModel> Modellist;
    //the context object
    private Context mCtx;

    public inventory_adapter(List<inventModel> modellist, Context mCtx) {
        Modellist = modellist;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.inventmodel,null,false);
        return  new MYViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {

        inventModel pro = Modellist.get(position);


        holder.productname.setText(Modellist.get(position).getProductname());
        holder.description.setText(Modellist.get(position).getDescription());
        holder.quantity.setText(String.valueOf(Modellist.get(position).getQuantity()));
        holder.unit.setText(Modellist.get(position).getUnit());
        holder.per_unit_price.setText(String.valueOf(Modellist.get(position).getPer_unit_price()));


    }

    @Override
    public int getItemCount() {

        return Modellist.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder {

        private TextView productname;
        private TextView description;
        private TextView quantity;
        private TextView unit;
        private TextView per_unit_price;


        public MYViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.pdname);
            description = itemView.findViewById(R.id.des);
            quantity = itemView.findViewById(R.id.quan);
            unit = itemView.findViewById(R.id.unit);
            per_unit_price = itemView.findViewById(R.id.perunit);
        }
    }
}


