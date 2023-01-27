package com.example.zadanialista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edycja extends AppCompatActivity {
    EditText tytul,opis,czas;
    Button dodawanie;
    private DatabaseReference database;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodawnie);
        String id=getIntent().getStringExtra("id");
        tytul=findViewById(R.id.tytul);
        opis=findViewById(R.id.opiszadania);
        czas=findViewById(R.id.czasZadania);
        dodawanie=findViewById(R.id.dodaj);
        database= FirebaseDatabase.getInstance().getReference("zadania2")
        .child(id);
        database.get().addOnCompleteListener(this,task -> {
            if(task.isSuccessful()){
                MainModel model=task.getResult().getValue(MainModel.class);
                tytul.setText(model.getTytul());
                opis.setText(model.getOpis());
                czas.setText(model.getCzas());
            }
            else {
                Toast.makeText(Edycja.this,"Błąd"+task.getException(),Toast.LENGTH_SHORT).show();
            }
        });
        dodawanie.setOnClickListener(v -> {
            String t=tytul.getText().toString();
            String t1=opis.getText().toString();
            String t2=czas.getText().toString();

            MainModel mainModel=new MainModel(database.getKey(),t,t1,t2);
            database.setValue(mainModel)
                    .addOnCompleteListener(this,task -> {
                        if(task.isSuccessful()){
                            finish();
                        }
                        else {
                            Toast.makeText(Edycja.this,"Błąd"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }
}
