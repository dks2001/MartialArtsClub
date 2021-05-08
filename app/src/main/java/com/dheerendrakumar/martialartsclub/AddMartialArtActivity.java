package com.dheerendrakumar.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dheerendrakumar.martialartsclub.model.DatabaseHandler;
import com.dheerendrakumar.martialartsclub.model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity {

    EditText edtName,edtPrice,edtColor;
    Button addButton;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtColor = findViewById(R.id.edtColor);
        addButton = findViewById(R.id.addButton);
        databaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name  = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                String color = edtColor.getText().toString();

                try {

                    double priceValue = Double.parseDouble(price);
                    MartialArt martialArtObject = new MartialArt(0,name,priceValue,color);
                    databaseHandler.addMartialArt(martialArtObject);
                    Toast.makeText(AddMartialArtActivity.this, martialArtObject + "saved", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}