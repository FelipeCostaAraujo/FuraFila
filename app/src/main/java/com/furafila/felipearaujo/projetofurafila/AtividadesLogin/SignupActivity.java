package com.furafila.felipearaujo.projetofurafila.AtividadesLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.furafila.felipearaujo.projetofurafila.R;
import com.furafila.felipearaujo.projetofurafila.modelo.Pessoa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputpass2, inputcpf,inputNome;
    private ListView listV_dados;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    String Senha;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Cadastro Fura Fila");

         //Get Firebase auth instance

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        inputpass2 = (EditText)findViewById(R.id.pass2);
        inputcpf = (EditText)findViewById(R.id.cpf);
        inputNome = (EditText)findViewById(R.id.nome);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();



                String cpf =(inputcpf.getText().toString());
                String nome =(inputNome.getText().toString());
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(inputNome.getText().toString());
                pessoa.setEmail(inputEmail.getText().toString());
                pessoa.setPass(inputPassword.getText().toString());
                pessoa.setCpf(inputcpf.getText().toString());

                /* condicional para confirmacao de senha nao funcionando
                if(inputPassword !=  inputpass2 ){
                    Toast.makeText(getApplicationContext(), "Senhas diferentes!", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //input de dados no banco do firebase




                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Insira seu Email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Insira sua Senha!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nome)) {
                    Toast.makeText(getApplicationContext(), "Insira seu Nome!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cpf)) {
                    Toast.makeText(getApplicationContext(), "Insira Seu CPF !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Senha muito curta, insira no mínimo 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child("Pessoa").setValue(pessoa);

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);


                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Falha ao Autenticar." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


}

