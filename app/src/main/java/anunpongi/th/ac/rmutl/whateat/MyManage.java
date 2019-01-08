package anunpongi.th.ac.rmutl.whateat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyManage {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {
        this.context = context;
        MyOpenHelper myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }

    public long addValueToSQLite(String nameString,
                                 String suranmeString,
                                 String genderString,
                                 String ageString,
                                 String statureString) {


        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", nameString);
        contentValues.put("Surname", suranmeString);
        contentValues.put("Gender", genderString);
        contentValues.put("Age", ageString);
        contentValues.put("Stature", statureString);

        return sqLiteDatabase.insert("userTABER", null, contentValues);
    }



}
