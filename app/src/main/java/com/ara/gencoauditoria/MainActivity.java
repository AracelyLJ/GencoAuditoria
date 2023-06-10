package com.ara.gencoauditoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gencoauditoria.R;

public class MainActivity extends AppCompatActivity {
    
    private LinearLayout btnAuditar;
    private static final int CODIGO_PERMISOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            checarPermisos();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Por favor, asegúrate de que los permisos estén activados (CONFIG -> APPS ->" +
                            "App info -> GencoAuditoria -> Permisos")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setCancelable(false).show();
        }
        
        btnAuditar = findViewById(R.id.btnAuditar);
        btnAuditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRCodeReader.class);
                startActivity(intent);
            }
        });
        
    }


    public void checarPermisos() {
        int permisoCamara = ContextCompat
                .checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA);
        if (permisoCamara != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.CAMERA},
                    CODIGO_PERMISOS);
        }
    }
}