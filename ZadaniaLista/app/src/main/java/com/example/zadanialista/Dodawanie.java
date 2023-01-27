package com.example.zadanialista;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

public class Dodawanie extends AppCompatActivity {
    EditText tytul,opis,czas;
    Button dodawanie;
    private DatabaseReference database;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodawnie);
        tytul=findViewById(R.id.tytul);
        opis=findViewById(R.id.opiszadania);
        czas=findViewById(R.id.czasZadania);
        dodawanie=findViewById(R.id.dodaj);
        database= FirebaseDatabase.getInstance().getReference("zadania2");
        dodawanie.setOnClickListener(v -> {
            String t=tytul.getText().toString();
            String t1=opis.getText().toString();
            String t2=czas.getText().toString();
            DatabaseReference documentReference=
            database.push();
            MainModel mainModel=new MainModel(documentReference.getKey(),t,t1,t2);
            documentReference.setValue(mainModel)
                    .addOnCompleteListener(this,task -> {
                        if(task.isSuccessful()){
                            finish();
                        }
                        else {
                            Toast.makeText(Dodawanie.this,"Błąd"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }
}
