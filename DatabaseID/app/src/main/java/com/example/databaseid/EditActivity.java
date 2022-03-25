package com.example.databaseid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class EditActivity extends AppCompatActivity {

    String Nazev, Suroviny, Postup, nazev2;
    EditText textNazev, textSuroviny, textPostup;
    DatabaseReference reff;
    Recept recept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        Intent ht2= getIntent();
        Bundle b = ht2.getExtras();
        nazev2 = (String) b.get("nazev2");

        reff = FirebaseDatabase.getInstance().getReference("Recept").child(nazev2);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){

                try {

                    if(dataSnapshot.exists())
                    {
                        Nazev = dataSnapshot.child("nazev").getValue().toString();
                        Suroviny= dataSnapshot.child("suroviny").getValue().toString();
                        Postup  = dataSnapshot.child("postup").getValue().toString();

                        textNazev = findViewById(R.id.editNazev);
                        textSuroviny = findViewById(R.id.editSuroviny);
                        textPostup =  findViewById(R.id.editPostup);

                        textNazev.setText(Nazev);
                        textSuroviny.setText(Suroviny);
                        textPostup.setText(Postup);

                    }

                }catch (NullPointerException e){
                    e.printStackTrace();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }

        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent ht1 = new Intent(EditActivity.this, MainActivity.class);
                startActivity(ht1);

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void editDat(View view) {

        recept = new Recept();
        reff = FirebaseDatabase.getInstance().getReference("Recept");


        recept.setNazev(textNazev.getText().toString().trim());
        recept.setSuroviny(textSuroviny.getText().toString().trim());
        recept.setPostup(textPostup.getText().toString().trim());



        reff.child(recept.getNazev()).setValue(recept);
        if(!nazev2.equals(recept.getNazev()))

          reff.child(nazev2).removeValue();

        Toast.makeText(EditActivity.this, "Aktualizuji data.", Toast.LENGTH_SHORT).show();
        Intent ht1 = new Intent(EditActivity.this, MainActivity.class);
        startActivity(ht1);

    }
    public void odstranitData(View view){
        recept = new Recept();
        reff = FirebaseDatabase.getInstance().getReference("Recept");


        recept.setNazev(null);
        recept.setSuroviny(null);
        recept.setPostup(null);

        reff.child(nazev2).setValue(recept);

        Toast.makeText(EditActivity.this, "Mažu data.", Toast.LENGTH_SHORT).show();
        Intent ht1 = new Intent(EditActivity.this, MainActivity.class);
        startActivity(ht1);

    }



}

