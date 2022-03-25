package com.example.databaseid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecept extends AppCompatActivity {
    EditText nazev, suroviny, postup;
    Recept recept;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recept);
        nazev =  findViewById(R.id.editReceptName);
        suroviny = findViewById(R.id.editReceptResources);
        postup =  findViewById(R.id.editReceptProcess);

    }
    public void zapisData(View view) {

        nazev =  (EditText)findViewById(R.id.editReceptName);
        suroviny = (EditText)findViewById(R.id.editReceptResources);
        postup = (EditText) findViewById(R.id.editReceptProcess);

        if (TextUtils.isEmpty(nazev.getText().toString())) {
            Toast.makeText(this, "Zadej název", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(suroviny.getText().toString())) {
            Toast.makeText(this, "Zadej Suroviny", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(postup.getText().toString())) {
            Toast.makeText(this, "Zadej Postup", Toast.LENGTH_SHORT).show();
            return;
        }else{

        recept = new Recept();
        reff = FirebaseDatabase.getInstance().getReference("Recept");

        recept.setNazev(nazev.getText().toString().trim());
        recept.setSuroviny(suroviny.getText().toString().trim());
        recept.setPostup(postup.getText().toString().trim());

        reff.child(nazev.getText().toString().trim()).setValue(recept);
        Toast.makeText(AddRecept.this, "Zapisuji data.", Toast.LENGTH_SHORT).show();
        Intent ht1 = new Intent(AddRecept.this, MainActivity.class);
        startActivity(ht1);
    }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent ht1 = new Intent(AddRecept.this, MainActivity.class);
                startActivity(ht1);

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
