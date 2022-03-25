package com.example.databaseid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Activity extends AppCompatActivity {
    String Nazev, Suroviny, Postup;
    TextView textNazev,textSuroviny, textPostup;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        Intent ht2= getIntent();
        Bundle b = ht2.getExtras();
        Nazev = (String) b.get("nazev");
        textNazev = findViewById(R.id.textNazev);
        textSuroviny = findViewById(R.id.textSuroviny);
        textPostup = findViewById(R.id.textPostup);

        textNazev.setText(Nazev);


        reff = FirebaseDatabase.getInstance().getReference().child("Recept").child(Nazev);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){



                try {

                    if(dataSnapshot.exists())
                    {
                        Postup  = dataSnapshot.child("postup").getValue().toString();
                        Suroviny= dataSnapshot.child("suroviny").getValue().toString();

                        textNazev.setText(Nazev);
                        textSuroviny.setText(Suroviny);
                        textPostup.setText(Postup);
                    }


                }
                catch (NullPointerException e){
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
                Intent ht1 = new Intent(Activity.this, MainActivity.class);
                startActivity(ht1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void rozklikEdit(View view)
    {
        Intent ht2 = new Intent(Activity.this, EditActivity.class);
        ht2.putExtra("nazev2", Nazev);
        startActivity(ht2);

    }


}






