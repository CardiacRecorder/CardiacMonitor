package com.example.cardiacmonitor;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecordsActivity extends AppCompatActivity {

    MyDatabaseHelper myDatabaseHelper;
    SimpleCursorAdapter simpleCursorAdapter;

    ListView listView;
    TextView no_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myDatabaseHelper = new MyDatabaseHelper(RecordsActivity.this);
        SQLiteDatabase sqLiteDatabase =  myDatabaseHelper.getWritableDatabase();

        listView = findViewById(R.id.list_view);
        no_text = findViewById(R.id.no_text);

        loadData();

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Toast.makeText(RecordsActivity.this,"Click list: "+i,Toast.LENGTH_SHORT).show();
           }
       });



    }

    public void loadData()
    {
        simpleCursorAdapter = myDatabaseHelper.populateListViewFromDB();
        listView.setAdapter(simpleCursorAdapter);

       // Toast.makeText(RecordsActivity.this,"::"+listView.getCount(),Toast.LENGTH_SHORT).show();

        if(listView.getCount()<1)
        {
            no_text.setText("No Records");
            no_text.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }
        else
        {
            no_text.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}