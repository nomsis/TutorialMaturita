package com.example.databaseid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    EditText nazev, suroviny, postup;
    RecyclerView recyclerView;
    DatabaseReference reff;
    ReceptAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recepts);
        reff = FirebaseDatabase.getInstance().getReference("Recept");
        recyclerView = findViewById(R.id.recyclerRecepts);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Recept> options1
                = new FirebaseRecyclerOptions.Builder<Recept>()
                .setQuery(reff, Recept.class)
                .build();

        adapter = new ReceptAdapter(options1);

        recyclerView.setAdapter(adapter);
        nazev =  findViewById(R.id.editReceptName);
        suroviny = findViewById(R.id.editReceptResources);
        postup =  findViewById(R.id.editReceptProcess);


    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void AddRecept(View view)
    {
        Intent AddRecept = new Intent(MainActivity.this, AddRecept.class);
        startActivity(AddRecept);

    }
}