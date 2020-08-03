package in.mangaldeep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

   public final static String DATABASE_NAME = "MyStudent.db";
   public final static String TABLE_NAME = "mystudent_table";
   public final static String COL_1 = "ID";
   public final static String COL_2 = "NAME";
   public final static String COL_3 = "EMAIL";
   public final static String COL_4 = "COURSE_COUNT";



 //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }




    //Methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, COURSE_COUNT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
      onCreate(db);
    }

    //Insert method
    public boolean insertData(String name, String email , String courseCount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, courseCount);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else {
            return  true;
        }
    }

    //Update method
    public boolean updateData(String id , String name, String email , String courseCount ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, courseCount);

        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;

    }

    //get data method

    public Cursor getData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID ='"+id+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    //Delete method

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID=?", new String[]{id});
    }

    //get all data method
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME ;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }















}
