package com.example.lio.misestudiantes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private EditText email, passw;
    private Button entrar;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //recuperamos los objetos controles
        email=(EditText)findViewById(R.id.editText);
        passw=(EditText)findViewById(R.id.editText2);
        entrar=(Button)findViewById(R.id.button);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entrar(v);
            }
        });
    }


    public void Entrar(View view){

        //convertimos a texto los valores introducidos y operamos
        String correo=email.getText().toString();
        String clave=passw.getText().toString();
        if ((correo.equals("baquero@uci.cu"))&(clave.equals("profesor"))){
            Autorizar();
        }
        if ((TextUtils.isEmpty(correo)) & (TextUtils.isEmpty(clave))) {
                toast.makeText(getApplicationContext(), "Debe llenar los campos requeridos", toast.LENGTH_LONG).show();
            } else {
                if (TextUtils.isEmpty(correo)) {
                    toast.makeText(getApplicationContext(), "Debe llenar el campo Email", toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(clave)) {
                    toast.makeText(getApplicationContext(), "Debe llenar el campo Password", toast.LENGTH_LONG).show();
                }
            }
        if (!correo.equals("baquero@uci.cu")&(!TextUtils.isEmpty(correo))&clave.equals("profesor")) {
                    toast.makeText(getApplicationContext(), "Email incorrecto", toast.LENGTH_LONG).show();
            }
        if (!clave.equals("profesor")&(!TextUtils.isEmpty(clave))&correo.equals("baquero@uci.cu")) {
                    toast.makeText(getApplicationContext(), "Password incorrecto", toast.LENGTH_LONG).show();
            }
        if ((!correo.equals("baquero@uci.cu"))&(!clave.equals("profesor"))&(!TextUtils.isEmpty(correo)) & (!TextUtils.isEmpty(clave))) {
            toast.makeText(getApplicationContext(), "Email y Password incorrectos", toast.LENGTH_LONG).show();
        }
    }

    public void Autorizar(){
        Intent ListEstudiantes = new Intent(getApplicationContext(), Insertar.class);
        startActivity(ListEstudiantes);
    }
}
