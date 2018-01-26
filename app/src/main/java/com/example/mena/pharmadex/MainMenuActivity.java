package com.example.mena.pharmadex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
/**
 * Created by Marco Ross
 */
public class MainMenuActivity extends AppCompatActivity {
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button scriptsBtn = (Button) findViewById(R.id.scripts);
        Button billingBtn = (Button) findViewById(R.id.billing);
        Button drugListBtn = (Button) findViewById(R.id.drug);
        bundle = getIntent().getExtras();
        scriptsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, PrescriptionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        billingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, BillingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        drugListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, InteractionsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
