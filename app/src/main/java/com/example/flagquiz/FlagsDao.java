package com.example.flagquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDao {
    public ArrayList<Flags> rastgele10Getir(Veritabani vt){
        ArrayList<Flags> flagsArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 10",null);

        while (c.moveToNext()){
            Flags b = new Flags(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    ,c.getString(c.getColumnIndex("bayrak_resim")));
            flagsArrayList.add(b);
        }
        return flagsArrayList;
    }

    public ArrayList<Flags> getRandom3Selection(Veritabani vt, int bayrak_id){
        ArrayList<Flags> flagsArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != "+bayrak_id+" ORDER BY RANDOM() LIMIT 3",null);

        while (c.moveToNext()){
            Flags b = new Flags(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    ,c.getString(c.getColumnIndex("bayrak_resim")));
            flagsArrayList.add(b);
        }
        return flagsArrayList;
    }
}
