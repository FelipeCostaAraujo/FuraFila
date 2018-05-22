package com.furafila.felipearaujo.projetofurafila.Agendamento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.furafila.felipearaujo.projetofurafila.R;

public class ActivityAgendamento extends AppCompatActivity {

    EditText email,password,pass2,cpf;
    ListView listV_dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);




    }
}
