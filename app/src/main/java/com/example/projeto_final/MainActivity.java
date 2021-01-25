package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button on;
    Button off;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        on = findViewById(R.id.button_on);
        off = findViewById(R.id.button_off);
        status = findViewById(R.id.txtStatus);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://projeto-final-a6269-default-rtdb.firebaseio.com/");
        DatabaseReference ref = database.getReference("LED_STATUS");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int st = Integer.parseInt(dataSnapshot.getValue().toString());
                if(st == 1){
                    status.setText("LIGADA"); //muda status mostrado na tela
                }else{
                    status.setText("DESLIGADA"); //muda status mostrado na tela
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        on.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Write a message to the database
                DatabaseReference myRef = database.getReference("LED_STATUS");//LED_STATUS, variável com o status do led dentro do banco firebase
                myRef.setValue(1);
                status.setText("LIGADA"); //muda status mostrado na tela
            }
        });

        off.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.getReference("LED_STATUS");//LED_STATUS, variável com o status do led dentro do banco firebase
                myRef.setValue(0);
                status.setText("DESLIGADA");
            }
        });

    }
}