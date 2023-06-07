package com.ara.gencoauditoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gencoauditoria.R;

import java.util.Arrays;
import java.util.List;

public class Resumen extends AppCompatActivity {

    private Button btnFinalizar;
    private LinearLayout llInfo;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        llInfo = findViewById(R.id.llInfo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultado = getIntent().getExtras().getString("resultado");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Resumen.this);
            builder.setMessage("Error obteniendo datos. Contacte al administrador.")
                    .setPositiveButton("REGRESAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Resumen.this, MainActivity.class));
                            finish();
                        }
                    })
                    .setCancelable(false).show();
        }

        String resSplit[] = resultado.split(",");
        for (String r: resSplit) {

            String r2 [] = r.split(":");

            if (r2.length != 2) continue;

            String key = r2[0].replace(" ","");
            String value = r2[1];
            switch (key){
                case "GT":
                    key = "Ganancia Total";
                    break;
                case "GP":
                    key = "Ganancia Parcial (desde el ultimo QR generado)";
                    break;
                case "T.T1":
                    key = "Total Token 1 (Historico total de los token 1 dispensados)";
                    break;
                case "P.T1":
                    key = "Parcial Token 1 (Historitco parcial desde ultimo Qr de los token 1)";
                    break;
                default:
                    continue;
            }

            TextView tvKey = new TextView(this);
            TextView tvValue = new TextView(this);


            tvKey.setTextColor(getColor(R.color.white));
            tvKey.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            tvKey.setText(key);

            tvValue.setTextColor(getColor(R.color.white));
            tvValue.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            tvValue.setText(value);


            llInfo.addView(tvKey);
            llInfo.addView(tvValue);

        }


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resumen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}