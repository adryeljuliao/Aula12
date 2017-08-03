package com.juliao.adryel.aula1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityA extends AppCompatActivity {

    TextView labelTexto;
    EditText editarValor;
    boolean tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Bundle data = getIntent().getExtras();

        tipo = data.getBoolean("tipo");

        editarValor = (EditText) findViewById(R.id.editTextNumero);
        labelTexto = (TextView) findViewById(R.id.labelNomeAtributo);

        if(tipo){
            labelTexto.setText("Peso ");
            String valor = data.getString("valorPeso");
            editarValor.setText(valor);

        }else{
            labelTexto.setText("Altura ");
            String valor = data.getString("valorAltura");

            editarValor.setText(valor);
        }

    }

    public void clickAlterar(View v){
        Intent intent = new Intent(this, ActivityA.class);
        Bundle param = getIntent().getExtras();


        param.putString("valorEditado", editarValor.getText().toString());
        intent.putExtras(param);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}
