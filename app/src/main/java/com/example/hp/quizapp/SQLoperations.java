package com.example.hp.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SQLoperations {
    String tablename = "Score";
    Context context;
    SQLiteDatabase db;
    int incorrect;
    Float percent;
    private static SQLoperations sqLoperations;
    public SQLoperations(Context context)
    {
        this.context = context;
        db = context.openOrCreateDatabase("ScoreDb", Context.MODE_PRIVATE, null);
    }

    void CreateTable()
    {

        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " +tablename+ "(correct INTEGER, incorrect INTEGER, score VARCHAR, percentage FLOAT, total INTEGER)");
        } catch (Exception e) {
            throw e;
        }
    }
    void AddScore(int correct, int total)
    {

        incorrect = total - correct;
        float a = (float)correct;
        float b = (float)total;
        percent = (a/b)*100;

        db.execSQL("INSERT INTO " +tablename+ " VALUES(" +correct+ ", " +incorrect+ ", '" +correct+ " / " +total+ "', " +percent+ ", " +total+ ")");
    }
    ArrayList<String> DisplayPrevScores()
    {
        ArrayList<String> Score = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT * FROM " +tablename+ "",null);
        if(c.moveToFirst())
        {
            do{
                int index = c.getColumnIndex("percentage");
                String percentage = String.valueOf(c.getFloat(index));
                index = c.getColumnIndex("score");
                String correct = c.getString(index);
                Score.add("Percentage: " + percentage + "%" + " Score: " + correct);
            }while (c.moveToNext());

        }

        return Score;
    }

    void Clear()
    {
        db.execSQL("DELETE FROM " +tablename+ "");
    }
}
