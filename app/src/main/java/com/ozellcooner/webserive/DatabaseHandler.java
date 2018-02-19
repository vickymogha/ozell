package com.ozellcooner.webserive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ozellcooner.fragment.model.Analogou;
import com.ozellcooner.fragment.model.AnalogousComplimentary;
import com.ozellcooner.fragment.model.ColorFamily;
import com.ozellcooner.fragment.model.Complimentary;
import com.ozellcooner.fragment.model.Datum;
import com.ozellcooner.fragment.model.Monochromatic;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_COLOR_FAMILY = "color_family";
    private static final String TABLE_COLOR = "color_table";

    // Contacts Table Columns names
   // private static final String KEY_ID = "id";
    private static final String KEY_FAMILY_COLOR = "color_name";
    private static final String KEY_FAMILY_DES = "description";
    private static final String KEY_FAMILY_RGB = "rgb";


    private static final String KEY_COLOR_MAIN_RGB = "color_name";
    private static final String KEY_COLOR_FAMILY = "color_family";
    private static final String KEY_MAIN_COLOR_CODE = "main_color_code";
    private static final String KEY_MAIN_COLOR_NAME = "main_color_name";
    private static final String KEY_MONOCHROMATIC = "monochromatic";
    private static final String KEY_COMPLIMENTRY = "complimentary";
    private static final String KEY_ANALOGOUS = "analogous";
    private static final String KEY_ANALOGOUS_COMP = "analogous_complimentary";




    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COLOR_TABLE = "CREATE TABLE " + TABLE_COLOR + "("
                + KEY_COLOR_MAIN_RGB + " TEXT,"
                + KEY_COLOR_FAMILY + " TEXT,"
                + KEY_MAIN_COLOR_CODE + " TEXT,"
                + KEY_MAIN_COLOR_NAME + " TEXT,"
                + KEY_MONOCHROMATIC + " TEXT,"
                + KEY_COMPLIMENTRY + " TEXT,"
                + KEY_ANALOGOUS + " TEXT,"
                + KEY_ANALOGOUS_COMP + " TEXT" + ")";
        db.execSQL(CREATE_COLOR_TABLE);

        String CREATE_COLOR_FAMILY = "CREATE TABLE " + TABLE_COLOR_FAMILY + "("
                + KEY_FAMILY_COLOR + " TEXT,"
                + KEY_FAMILY_DES + " TEXT,"
                + KEY_FAMILY_RGB + " TEXT" + ")";
        db.execSQL(CREATE_COLOR_FAMILY);
    }


    public void addFamilyColor(ColorFamily familyColor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FAMILY_COLOR, familyColor.getColor());
        values.put(KEY_FAMILY_DES, familyColor.getDesc());
        values.put(KEY_FAMILY_RGB, familyColor.getRgb());

        // Inserting Row
        db.insert(TABLE_COLOR_FAMILY, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<ColorFamily> getAllColorFamily() {
        ArrayList<ColorFamily> contactList = new ArrayList<ColorFamily>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COLOR_FAMILY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ColorFamily contact = new ColorFamily();
                contact.setColor(cursor.getString(0));
                contact.setDesc(cursor.getString(1));
                contact.setRgb(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public ArrayList<Datum> getAllColors() {
        ArrayList<Datum> contactList = new ArrayList<Datum>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COLOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Datum contact = new Datum();
                contact.setMainRgb(cursor.getString(0));
                contact.setFamily(cursor.getString(1));
                contact.setMainColorCode(cursor.getString(2));
                contact.setMainColorName(cursor.getString(3));

               String jsonMono = cursor.getString(4);
                String jsonCompl = cursor.getString(5);
                String jsonAnol = cursor.getString(6);
                String jsonAnologCompl = cursor.getString(7);

                Gson gson = new Gson();
                Type listTypeMonoch = new TypeToken<ArrayList<Monochromatic>>() {}.getType();
                ArrayList<Monochromatic> listMono = gson.fromJson(jsonMono, listTypeMonoch);

                Type listTypeAnog = new TypeToken<ArrayList<Analogou>>() {}.getType();
                ArrayList<Analogou> listAnalogus = gson.fromJson(jsonAnol, listTypeAnog);

                Type listTypeCompli = new TypeToken<ArrayList<Complimentary>>() {}.getType();
                ArrayList<Complimentary> listComplimat = gson.fromJson(jsonCompl, listTypeCompli);

                Type listTypeAnogausCompl = new TypeToken<ArrayList<AnalogousComplimentary>>() {}.getType();
                ArrayList<AnalogousComplimentary> listAnoglCompl = gson.fromJson(jsonAnologCompl, listTypeAnogausCompl);


                contact.setAnalogous(listAnalogus);
                contact.setComplimentary(listComplimat);
                contact.setAnalogousComplimentary(listAnoglCompl);
                contact.setMonochromatic(listMono);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public ArrayList<Datum> getAllColors(ColorFamily color) {
        ArrayList<Datum> contactList = new ArrayList<Datum>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COLOR + " Where " + KEY_COLOR_FAMILY+"='"+color.getColor()+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Datum contact = new Datum();
                contact.setMainRgb(cursor.getString(0));
                contact.setFamily(cursor.getString(1));
                contact.setMainColorCode(cursor.getString(2));
                contact.setMainColorName(cursor.getString(3));

                String jsonMono = cursor.getString(4);
                String jsonCompl = cursor.getString(5);
                String jsonAnol = cursor.getString(6);
                String jsonAnologCompl = cursor.getString(7);

                Gson gson = new Gson();
                Type listTypeMonoch = new TypeToken<ArrayList<Monochromatic>>() {}.getType();
                ArrayList<Monochromatic> listMono = gson.fromJson(jsonMono, listTypeMonoch);

                Type listTypeAnog = new TypeToken<ArrayList<Analogou>>() {}.getType();
                ArrayList<Analogou> listAnalogus = gson.fromJson(jsonAnol, listTypeAnog);

                Type listTypeCompli = new TypeToken<ArrayList<Complimentary>>() {}.getType();
                ArrayList<Complimentary> listComplimat = gson.fromJson(jsonCompl, listTypeCompli);

                Type listTypeAnogausCompl = new TypeToken<ArrayList<AnalogousComplimentary>>() {}.getType();
                ArrayList<AnalogousComplimentary> listAnoglCompl = gson.fromJson(jsonAnologCompl, listTypeAnogausCompl);


                contact.setAnalogous(listAnalogus);
                contact.setComplimentary(listComplimat);
                contact.setAnalogousComplimentary(listAnoglCompl);
                contact.setMonochromatic(listMono);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public void addColor(Datum color) {
        SQLiteDatabase db = this.getWritableDatabase();

        Gson gson = new Gson();
        Type listTypeMonoch = new TypeToken<ArrayList<Monochromatic>>() {}.getType();
        String jsonMonochromatic = gson.toJson(color.getMonochromatic(), listTypeMonoch);

        Type listTypeAnog = new TypeToken<ArrayList<Analogou>>() {}.getType();
        String jsonAnalogus = gson.toJson(color.getAnalogous(), listTypeAnog);

        Type listTypeCompli = new TypeToken<ArrayList<Complimentary>>() {}.getType();
        String jsonComplimat = gson.toJson(color.getComplimentary(), listTypeCompli);

        Type listTypeAnogausCompl = new TypeToken<ArrayList<AnalogousComplimentary>>() {}.getType();
        String jsonAnoglCompl = gson.toJson(color.getAnalogousComplimentary(), listTypeAnogausCompl);


        ContentValues values = new ContentValues();
        values.put(KEY_COLOR_MAIN_RGB, color.getMainRgb());
        values.put(KEY_COLOR_FAMILY, color.getFamily());
        values.put(KEY_MAIN_COLOR_CODE, color.getMainColorCode());
        values.put(KEY_MAIN_COLOR_NAME, color.getMainColorName());

        values.put(KEY_MONOCHROMATIC, jsonMonochromatic);
        values.put(KEY_ANALOGOUS, jsonAnalogus);
        values.put(KEY_COMPLIMENTRY, jsonComplimat);
        values.put(KEY_ANALOGOUS_COMP, jsonAnoglCompl);


        // Inserting Row
        db.insert(TABLE_COLOR, null, values);
        db.close(); // Closing database connection
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
       // onCreate(db);
    }
}