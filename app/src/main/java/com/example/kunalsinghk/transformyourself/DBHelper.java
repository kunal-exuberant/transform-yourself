/**
 * Created by kunalsingh.k on 31/07/15.
 */
package com.example.kunalsinghk.transformyourself;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String GOALS_TABLE_NAME = "goals";
    public static final String GOALS_COLUMN_ID = "id";
    public static final String GOALS_COLUMN_NAME = "goal";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
             //   "create table contacts " +
             //           "(id integer primary key, name text,phone text,email text, street text,place text)"

                "create table goals " +
                        "(id integer primary key, goal text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS goals");
        onCreate(db);
    }

    public boolean insertGoals(String goal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("goal", goal);
        db.insert("goals", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from goals where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, GOALS_TABLE_NAME);
        return numRows;
    }

    public boolean updateGoal (Integer id, String goal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("goal", goal);
        db.update("goal", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteGoal (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("goals",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllGoalss()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from goals", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(GOALS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}