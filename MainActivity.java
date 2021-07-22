package com.levv.july;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void toMakeEntry(View view){
        Intent intent = new Intent(getApplicationContext(), makeEntry.class);
        startActivity(intent);
    }

    public void toPersonalEntry(View view){
        Intent intent = new Intent(getApplicationContext(), PersonalEntry.class);
        startActivity(intent);
    }

    public void toViewLog(View view){
        Intent intent = new Intent(getApplicationContext(), ViewLog.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
