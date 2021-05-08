package com.dheerendrakumar.martialartsclub;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.dheerendrakumar.martialartsclub.model.DatabaseHandler;
import com.dheerendrakumar.martialartsclub.model.MartialArt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHandler databaseHandler;
    private double totalMartialArtPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtPrice = 0.0;
        scrollView = findViewById(R.id.scrollview);

        Point screenWidth = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenWidth);
        martialArtButtonWidth = screenWidth.x / 2;


        modifyUserInterface();

    }

    public void modifyUserInterface() {

        ArrayList<MartialArt> allMartialArtObject = databaseHandler.returnAllMartialArtObject();
        scrollView.removeAllViewsInLayout();
        if(allMartialArtObject.size()>0) {


            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObject.size() + 1)/2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[allMartialArtObject.size()];

            int index=0;
            for(MartialArt martialArtObject: allMartialArtObject) {

                martialArtButtons[index] = new MartialArtButton(MainActivity.this,martialArtObject);

                martialArtButtons[index].setText(martialArtObject.getMartialArtID() + "\n" + martialArtObject.getMartialArtName()
                                                + "\n" + martialArtObject.getMartialArtPrice());

                switch (martialArtObject.getMartialArtColor()){

                    case "Red":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Black":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Purple":
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Green":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "Yellow":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);

                }

                martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index],martialArtButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            }

            scrollView.addView(gridLayout);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){

            case R.id.add_martial_art:
                Intent intent = new Intent(MainActivity.this,AddMartialArtActivity.class);
                startActivity(intent);
                return true;

            case R.id.delete_martial_art:
                Intent intent1 = new Intent(MainActivity.this,DeleteMartialArtActivity.class);
                startActivity(intent1);
                return true;

            case R.id.update_martial_art:
                Intent intent2 = new Intent(MainActivity.this,UpdateMartialArtActivity.class);
                startActivity(intent2);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMartialArtPrice = totalMartialArtPrice + martialArtButton.MartialArtPrice();
        String martialArtPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtPrice);
        Toast.makeText(this, martialArtPriceFormatted, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }
}