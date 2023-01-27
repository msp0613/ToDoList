package com.example.zadanialista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<MainModel>list;
    AdapterListener listener;
    public MyAdapter(Context context,ArrayList<MainModel> list,AdapterListener listener) {

        this.context=context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.main_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MainModel model=list.get(position);
        holder.nametext.setText(model.getTytul());
        holder.opis.setText(model.getOpis());
        holder.czas.setText(model.getCzas());
        holder.usun.setOnClickListener(v -> {
            listener.delete(model.id);
        });
        holder.edytuj.setOnClickListener(v -> {
            listener.edit(model.id);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface AdapterListener{
        void delete(
                String id
        );
        void edit(String id);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
    TextView nametext,opis,czas;
    Button usun,edytuj;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nametext=itemView.findViewById(R.id.nametext);
            opis=itemView.findViewById(R.id.opis);
            czas=itemView.findViewById(R.id.czas);
            usun=itemView.findViewById(R.id.usun);
            edytuj=itemView.findViewById(R.id.edytuj);
        }
    }
}
