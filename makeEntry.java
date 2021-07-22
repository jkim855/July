package com.levv.july;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class makeEntry extends AppCompatActivity {

    public ArrayList<String> array = new ArrayList<>();
    public TextView prompt;
    public List<String> log;

    public void toViewLog(View view){
        Intent intent = new Intent(getApplicationContext(), ViewLog.class);
        //intent.putStringArrayListExtra("log", (ArrayList<String>) log);
        startActivity(intent);
    }

    public void saveFunc(View view){
        String userInput = ((TextView) findViewById(R.id.userInput)).getText().toString();
        if(userInput.trim().length() > 0) {
            //log.add(prompt.getText().toString() + ":\n" + userInput);

            SharedPreferences pastEntries = this.getSharedPreferences("com.levv.july", Context.MODE_PRIVATE);
            DateFormat df = new SimpleDateFormat("EEEE dd MMM yyyy, hh:mma");
            String currentTime = df.format(Calendar.getInstance().getTime());
            pastEntries.edit().putString(currentTime + "\n" + prompt.getText().toString() + "\n\n" + userInput, "").apply();

            rerollFunc();
            ((TextView)findViewById(R.id.userInput)).setText("");
        }
        else{
            Toast.makeText(this, "Type something and try saving again", Toast.LENGTH_SHORT).show();
        }
    }

    public void rerollFunc(){
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is exclusive. Should be good with output for array.
        int randomIndex = rand.nextInt(array.size());

        // Print your random quote...
        prompt.setText(array.get(randomIndex));
    }

    public void rerollClick(View view){
        rerollFunc();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry);

        log = new ArrayList<String>();
        prompt = (TextView)findViewById(R.id.prompt);

        InputStream is = this.getResources().openRawResource(R.raw.prompts);

        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                array.add(line);
            }
        } catch(IOException ie) {
            ie.printStackTrace();
        }

        rerollFunc();
    }
}
