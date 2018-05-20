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
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        pass2 = (EditText)findViewById(R.id.pass2);
        cpf = (EditText)findViewById(R.id.cpf);

        listV_dados = (ListView) findViewById(R.id.listV_dados);



    }
}
