package com.furafila.felipearaujo.projetofurafila.Agendamento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.furafila.felipearaujo.projetofurafila.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Consulta_Activity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Button Consultar;
    EditText cpf,data,horario,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        Consultar =(Button)findViewById(R.id.btn_Consulta1);
        data = (EditText)findViewById(R.id.txt_dataC);
        horario = (EditText)findViewById(R.id.txtHorarioC);
        cpf = (EditText)findViewById(R.id.txtCpfC);
        email =(EditText)findViewById(R.id.txt_emailC);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Atendimento").child("13:30").child("email").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        email.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                databaseReference.child("Atendimento").child("13:30").child("cpf").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       cpf.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                databaseReference.child("Atendimento").child("13:30").child("dt_agendamento").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        data.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                       data.setText("ERRO");
                    }
                });

                databaseReference.child("Atendimento").child("13:30").child("hr_agendamento").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        horario.setText(String.valueOf(dataSnapshot.getValue()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        }


    });


}
}

