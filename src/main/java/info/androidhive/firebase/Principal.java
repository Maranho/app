package info.androidhive.firebase;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    private Button btnSair, btnConta, btnAvaliar, btnDuvidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);



        FloatingActionButton btnDuvidas  = findViewById(R.id.btn_duvidas);
        FloatingActionButton btnConta =  findViewById(R.id.btn_conta);
        FloatingActionButton btnAvaliar =  findViewById(R.id.btn_avaliar);
        btnSair =   findViewById(R.id.btn_sair);

        btnSair.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });



        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),AvaliacaoActivity.class);
                startActivity(intent1);
            }
        });

        btnDuvidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(),Chatbot.class);
                startActivity(intent1);


            }
        });

        btnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent1);

            }
        });







    }




    }

