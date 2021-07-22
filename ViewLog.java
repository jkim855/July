package com.levv.july;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class ViewLog extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);

        //ArrayList<String> log = getIntent().getStringArrayListExtra("log");
        final ArrayList<String> log = new ArrayList<>();
        final SharedPreferences pastEntries = this.getSharedPreferences("com.levv.july", Context.MODE_PRIVATE);
        Map<String,?> keys = pastEntries.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            log.add(entry.getKey());// + "\n" + entry.getValue().toString());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, log);

        ListView logTable = (ListView) findViewById(R.id.logTable);
        logTable.setAdapter(arrayAdapter);
        /*
        logTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                pastEntries.edit().remove(String.valueOf(position)).apply();
                arrayAdapter.remove(arrayAdapter.getItem(position));
                log.remove(position);
            }
        });
        */

        logTable.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){

                final int deleteInd = i;

                new AlertDialog.Builder(ViewLog.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pastEntries.edit().remove(log.get(deleteInd)).apply();
                                log.remove(deleteInd);
                                arrayAdapter.notifyDataSetChanged();
                                if(log.isEmpty()){
                                    findViewById(R.id.noEntryText).setAlpha(1f);
                                }
                            }
                        }
                        )
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

        if(arrayAdapter.getCount() == 0){
            findViewById(R.id.noEntryText).setAlpha(1f);
        }
        else{
            findViewById(R.id.noEntryText).setAlpha(0f);
        }
    }
}
