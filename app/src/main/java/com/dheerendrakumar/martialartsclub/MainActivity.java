package com.dheerendrakumar.martialartsclub;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
}