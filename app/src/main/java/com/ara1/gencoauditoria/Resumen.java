package com.ara1.gencoauditoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ara1.gencoauditoria.R;

public class Resumen extends AppCompatActivity {

    private TextView btnFinalizar;
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
                    key = "\nGanancia total x Venta Total";
                    break;
                case "GP":
                    key = "\nGanancia Parcial x Venta Parcial";
                    break;
                case "T.T1":
                    key = "\nTotal de toquens por:\n\nTotal de Medallas (Histórico total de medallas dispensadas)";
                    break;
                case "P.T1":
                    key = "\nParcial de Medallas ( Histórico parcial de medallas dispensadas desde el último QR) \n";
                    break;
                default:
                    continue;
            }

            TextView tvKey = new TextView(this);
            TextView tvValue = new TextView(this);
            View line = new View(this);


            tvKey.setTextColor(getColor(R.color.white));
            tvKey.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            tvKey.setText(key);
            tvKey.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
//            tvKey.setBackgroundColor(getColor(R.color.dorado1));
//            tvKey.setElevation(60);
//            tvKey.setShadowLayer(15,100,10,R.color.white);

            tvValue.setTextColor(getColor(R.color.white));
            tvValue.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            tvValue.setText(value);
            tvValue.setPadding(0,0,0,20);
            tvValue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 4
            );
            line.setLayoutParams(params);
            line.setBackgroundResource(R.drawable.cardview_rectangulo_dorado);
            llInfo.addView(tvKey);
            llInfo.addView(tvValue);
            llInfo.addView(line);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Resumen.this, MainActivity.class);
        startActivity(intent);
    }
}