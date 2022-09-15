package com.example.mycashbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.mycashbook.databinding.ActivityDetailCashFlowBinding;
import com.example.mycashbook.utils.DatabaseHelper;
import com.example.mycashbook.utils.MyAdapter;
import com.example.mycashbook.utils.Utils;

import java.util.ArrayList;

public class DetailCashFlowActivity extends AppCompatActivity {

    private ActivityDetailCashFlowBinding binding;
    private Utils util;
    private DatabaseHelper dbHelper;
    private MyAdapter adapter;

    ArrayList<String> tanggal, nominal, keterangan, kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_cash_flow);
        util = new Utils();

        dbHelper = new DatabaseHelper(DetailCashFlowActivity.this);
        tanggal = new ArrayList<>();
        nominal = new ArrayList<>();
        keterangan = new ArrayList<>();
        kategori = new ArrayList<>();

        storeDataInArrays();

        adapter = new MyAdapter(
                DetailCashFlowActivity.this,
                this,
                tanggal,
                nominal,
                keterangan,
                kategori);

        binding.rvKeuangan.setAdapter(adapter);
        binding.rvKeuangan.setLayoutManager(new LinearLayoutManager(
                DetailCashFlowActivity.this));

        binding.btKembaliCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.moveToMenu(DetailCashFlowActivity.this, MainActivity.class);
            }
        });
    }

    private void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            binding.imEmpty.setVisibility(View.VISIBLE);
            binding.tvEmpty.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                tanggal.add(cursor.getString(1));
                nominal.add(cursor.getString(2));
                keterangan.add(cursor.getString(3));
                kategori.add(cursor.getString(4));
            }
            binding.imEmpty.setVisibility(View.GONE);
            binding.tvEmpty.setVisibility(View.GONE);
        }
    }
}