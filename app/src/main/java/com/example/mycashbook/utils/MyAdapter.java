package com.example.mycashbook.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycashbook.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList tanggal, nominal, keterangan, kategori;

    public MyAdapter(Context context, Activity activity, ArrayList tanggal
            , ArrayList nominal, ArrayList keterangan, ArrayList kategori) {
        this.context = context;
        this.activity = activity;
        this.tanggal = tanggal;
        this.nominal = nominal;
        this.keterangan = keterangan;
        this.kategori = kategori;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cash_flow, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Utils utils = new Utils();

        if (kategori.get(position).toString().equals("pengeluaran")){
            holder.tv_kategori.setText("[-]");
            holder.im_kategori.setImageResource(R.drawable.right_arrow);
        }else{
            holder.tv_kategori.setText("[+]");
            holder.im_kategori.setImageResource(R.drawable.left_arrow);
        }

        double nominal_rupiah = Double.parseDouble(nominal.get(position).toString());
        String nominal_rupiah_str = utils.setFormatRupiah(nominal_rupiah);

        holder.tv_nominal.setText(nominal_rupiah_str);
        holder.tv_keterangan.setText(keterangan.get(position).toString());
        holder.tv_tanggal.setText(tanggal.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return tanggal.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_kategori, tv_nominal, tv_keterangan, tv_tanggal;
        ImageView im_kategori;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kategori = itemView.findViewById(R.id.tv_kategori);
            tv_nominal = itemView.findViewById(R.id.tv_nominal);
            tv_keterangan = itemView.findViewById(R.id.tv_keterangan);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            im_kategori = itemView.findViewById(R.id.im_kategori);
        }
    }
}
