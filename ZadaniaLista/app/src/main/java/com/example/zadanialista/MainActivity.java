package com.example.zadanialista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MyAdapter.AdapterListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<MainModel>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.rv);
        database= FirebaseDatabase.getInstance().getReference("zadania2");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list,this);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MainModel model=dataSnapshot.getValue(MainModel.class);
                    list.add(model);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        findViewById(R.id.dodajZadanie).setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,Dodawanie.class);
            startActivity(intent);
        });
    }

    @Override
    public void delete(String id) {
        database.child(id).removeValue().addOnCompleteListener(this,task -> {
            if(task.isSuccessful()){
                Toast.makeText(MainActivity.this,"Element usunięty",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this,"Błąd"+task.getException(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void edit(String id) {
        Intent intent=new Intent(MainActivity.this,Edycja.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}