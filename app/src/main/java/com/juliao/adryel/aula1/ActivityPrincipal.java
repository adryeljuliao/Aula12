package com.juliao.adryel.aula1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ActivityPrincipal extends AppCompatActivity {
    public static final int PESO = 11;
    public static final int ALTURA = 22;
    TextView peso;
    TextView altura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        peso = (TextView) findViewById(R.id.labelValorPeso);
        altura = (TextView) findViewById(R.id.labelValorAlt);
    }

    public void click(View v){
        Button botao = (Button)v;

        if(botao.getId() == R.id.botPeso){

            Intent intent = new Intent(this, ActivityA.class);
            Bundle param = new Bundle();

            param.putString("valorPeso", peso.getText().toString());
            param.putBoolean("tipo", true);
            intent.putExtras(param);

            startActivityForResult(intent, PESO);

        }else if(botao.getId() == R.id.botAlt){

            Intent intent = new Intent(this, ActivityA.class);
            Bundle param = new Bundle();

            param.putString("valorAltura", altura.getText().toString());
            param.putBoolean("tipo", false);
            intent.putExtras(param);

            startActivityForResult(intent, ALTURA);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PESO) {
            //De qual opção veio o resultado?
            if(resultCode == RESULT_OK) {
                Bundle dados = data.getExtras();
                String editado = dados.getString("valorEditado");
                peso.setText(editado);


            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
            }


        } else if(requestCode == ALTURA){
                if(resultCode == RESULT_OK) {
                    Bundle dados = data.getExtras();
                    String editado = dados.getString("valorEditado");
                    altura.setText(editado);

                }else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
                }
        }

    }

    public void calcular(View v){
        String msg = null;
        Double nPeso = Double.parseDouble(peso.getText().toString());
        Double nAltura = Double.parseDouble(altura.getText().toString());

        Double resultadoIMC = nPeso / (nAltura * nAltura);

        if(resultadoIMC < 16){
            msg = "Desnutrido com grau 1000%";
        } else if(resultadoIMC > 16 && resultadoIMC < 17){
            msg = "Desnutrido com grau 500%";
        } else if(resultadoIMC > 17 && resultadoIMC < 18.5){
            msg = "Desnutrido";
        } else if(resultadoIMC > 18.5 && resultadoIMC < 25){
            msg = "1000% Saudável";
        } else if(resultadoIMC > 25 && resultadoIMC < 30){
            msg = "Sobrepeso";
        } else if(resultadoIMC > 30 && resultadoIMC < 35){
            msg = "Obeso com grau 500%";
        } else if(resultadoIMC > 35 && resultadoIMC < 40){
            msg = "Obeso com grau 1000%";
        } else if(resultadoIMC > 40){
            msg = "Um Majin Boo";
        }

        TextView resultado = (TextView) findViewById(R.id.labelValorResultado);
        DecimalFormat df = new DecimalFormat("#.##");
        resultado.setText("Seu IMC é: " + df.format(resultadoIMC) +"\nVc é: " + msg);

    }




}
