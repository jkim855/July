package com.levv.july;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonalEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_entry);
    }

    public void toViewLog(View view){
        Intent intent = new Intent(getApplicationContext(), ViewLog.class);
        startActivity(intent);
    }

    public void saveFunc(View view){
        String userInput = ((TextView) findViewById(R.id.userInput)).getText().toString();
        String userPrompt = ((TextView) findViewById(R.id.prompt)).getText().toString();
        if(userInput.trim().length() > 0 && userPrompt.trim().length() > 0) {

            TextView prompt = (TextView)findViewById(R.id.prompt);
            SharedPreferences pastEntries = this.getSharedPreferences("com.levv.july", Context.MODE_PRIVATE);
            DateFormat df = new SimpleDateFormat("EEEE dd MMM yyyy, hh:mma");
            String currentTime = df.format(Calendar.getInstance().getTime());
            pastEntries.edit().putString(currentTime + "\n" + prompt.getText().toString() + "\n\n" + userInput, "").apply();

            ((TextView)findViewById(R.id.userInput)).setText("");
            ((TextView)findViewById(R.id.prompt)).setText("");
        }
        else{
            Toast.makeText(this, "One of the input fields are blank", Toast.LENGTH_SHORT).show();
        }
    }
}
