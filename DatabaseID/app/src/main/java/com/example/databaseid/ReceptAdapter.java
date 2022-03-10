package com.example.databaseid;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ReceptAdapter extends FirebaseRecyclerAdapter<Recept, ReceptAdapter.receptViewholder> {

    public ReceptAdapter(@NonNull FirebaseRecyclerOptions<Recept> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull receptViewholder holder, int position, Recept model) {
        holder.nazev.setText(model.getNazev());

    }

    public receptViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout, parent, false);
        return new ReceptAdapter.receptViewholder(view);
    }

    class receptViewholder extends RecyclerView.ViewHolder   {
        TextView nazev;
        public receptViewholder(@NonNull View itemView)
        {
            super(itemView);
            nazev= itemView.findViewById(R.id.Nazev);

            itemView.findViewById(R.id.btnRozklik).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ht1 = new Intent( view.getContext(), Activity.class);
                    ht1.putExtra("nazev", nazev.getText());
                    view.getContext().startActivity(ht1);

                    FirebaseDatabase.getInstance().getReference().child("Recept");
                }
            });
        }
    }
}
