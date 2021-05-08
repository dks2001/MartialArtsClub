package com.dheerendrakumar.martialartsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dheerendrakumar.martialartsclub.model.DatabaseHandler;
import com.dheerendrakumar.martialartsclub.model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);

        modifyUserInterface();
    }

    private void modifyUserInterface() {

        ArrayList<MartialArt> martialArtsObject = databaseHandler.returnAllMartialArtObject();
        if(martialArtsObject.size()>0) {

            ScrollView scrollView = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(martialArtsObject.size());
            gridLayout.setColumnCount(5);

            TextView[] textViews = new TextView[martialArtsObject.size()];
            EditText[][] edtNamePriceColor = new EditText[martialArtsObject.size()][3];
            Button[] buttons = new Button[martialArtsObject.size()];

            Point screensize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screensize);

            int screenwidth = screensize.x;
            int index = 0;

            for(MartialArt martialArt : martialArtsObject) {

                textViews[index] = new TextView(UpdateMartialArtActivity.this);
                textViews[index].setGravity(Gravity.CENTER);
                textViews[index].setText(martialArt.getMartialArtID() + "");

                edtNamePriceColor[index][0] = new EditText(UpdateMartialArtActivity.this);
                edtNamePriceColor[index][1] = new EditText(UpdateMartialArtActivity.this);
                edtNamePriceColor[index][2] = new EditText(UpdateMartialArtActivity.this);

                edtNamePriceColor[index][0].setText(martialArt.getMartialArtName());
                edtNamePriceColor[index][1].setText(martialArt.getMartialArtPrice()+"");
                edtNamePriceColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                edtNamePriceColor[index][2].setText(martialArt.getMartialArtColor());

                edtNamePriceColor[index][0].setId(martialArt.getMartialArtID() + 10);
                edtNamePriceColor[index][1].setId(martialArt.getMartialArtID() + 20);
                edtNamePriceColor[index][2].setId(martialArt.getMartialArtID() + 30);

                buttons[index] = new Button(UpdateMartialArtActivity.this);
                buttons[index].setText("Modify");
                buttons[index].setId(martialArt.getMartialArtID());
                buttons[index].setOnClickListener(UpdateMartialArtActivity.this);

                gridLayout.addView(textViews[index],(int)(screenwidth*0.05), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePriceColor[index][0],(int)(screenwidth * 0.20),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePriceColor[index][1],(int)(screenwidth * 0.20),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePriceColor[index][2],(int)(screenwidth * 0.20),ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(buttons[index],(int)(screenwidth * 0.35),ViewGroup.LayoutParams.WRAP_CONTENT);


                index++;

            }
            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }

    }

    @Override
    public void onClick(View v) {

        int matialArtObjectID = v.getId();

        EditText edtMartialArtName = (EditText) findViewById(matialArtObjectID + 10);
        EditText edtMartialArtPrice = (EditText) findViewById(matialArtObjectID + 20);
        EditText edtMartialArtColor = (EditText) findViewById(matialArtObjectID + 30);

        String nameValue = edtMartialArtName.getText().toString();
        String priceVale = edtMartialArtPrice.getText().toString();
        String colorValue = edtMartialArtColor.getText().toString();


        try {
            Double priceDoubleValue = Double.parseDouble(priceVale);
            databaseHandler.modifyMartialArtObject(matialArtObjectID,nameValue,priceDoubleValue,colorValue);
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }




    }
}