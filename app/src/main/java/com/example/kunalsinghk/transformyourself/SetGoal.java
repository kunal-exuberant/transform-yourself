package com.example.kunalsinghk.transformyourself;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;

import java.io.Console;


public class SetGoal extends ActionBarActivity {

    Button btnSetGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);
        btnSetGoal = (Button)findViewById(R.id.btnSetGoal);

        btnSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Loggin to console from set goal");
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.insertGoals("Inserting My first goal");
                System.out.println(dbHelper.getData(0));
                Cursor cursor = dbHelper.getData(0);


                String data[] = new String[cursor.getCount()];
                int i = 0;

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    data[i] = cursor.getString(0);
                    i++;
                    System.out.println(data[i]);
                    cursor.moveToNext();
                }
                System.out.println("Printitng the new goal");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_goal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
