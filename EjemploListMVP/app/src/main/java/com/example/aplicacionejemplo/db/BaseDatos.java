package com.example.aplicacionejemplo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.aplicacionejemplo.pojo.Contacto;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto="CREATE TABLE "+ConstantesBD.TABLE_CONTACTS+"(" +
                ConstantesBD.TABLE_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBD.TABLE_CONTACTS_NOMBRE + " TEXT, "+
                ConstantesBD.TABLE_CONTACTS_TELEFONO + " TEXT, "+
                ConstantesBD.TABLE_CONTACTS_EMAIL +" TEXT, "+
                ConstantesBD.TABLE_CONTACTS_FOTO + " INTEGER " + ")";

        String queryCrearTablaContactoLikes="CREATE TABLE "+ ConstantesBD.TABLE_LIKES_CONTACT + "(" +
                ConstantesBD.TABLE_LIKES_CONTACT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO + " INTEGER, " +
                ConstantesBD.TABLE_LIKES_CONTACT_NUMERO_LIKES + " INTEGER, "  +
                "FOREIGN KEY (" + ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO + ") " +
                "REFERENCES " + ConstantesBD.TABLE_CONTACTS + "(" + ConstantesBD.TABLE_CONTACTS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaContacto);
        db.execSQL(queryCrearTablaContactoLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBD.TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBD.TABLE_LIKES_CONTACT);
        onCreate(db);
    }

    public ArrayList<Contacto> obtenerTodosLosContactos(){
        ArrayList <Contacto> contactos = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBD.TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Contacto contactoActual = new Contacto();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setNombre(registros.getString(1));
            contactoActual.setTelefono(registros.getString(2));
            contactoActual.setEmail(registros.getString(3));
            contactoActual.setFoto(registros.getInt(4));

            String queryLikes="SELECT COUNT(" + ConstantesBD.TABLE_LIKES_CONTACT_NUMERO_LIKES + ") as Likes " +
                    " FROM " + ConstantesBD.TABLE_LIKES_CONTACT + " WHERE " +
                    ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contactoActual.getId();
            Cursor registroLikes = db.rawQuery(queryLikes, null);
            if(registroLikes.moveToNext()){
                contactoActual.setLikes(registroLikes.getInt(0));
            }else{
                contactoActual.setLikes(0);
            }
            contactos.add(contactoActual);
        }
        db.close();
        return contactos;
    }

    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_CONTACTS, null, contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_LIKES_CONTACT, null, contentValues);
        db.close();
    }

    public int obtenerLikesContacto(Contacto contacto){
        int likes=0;
        String query="SELECT COUNT(" + ConstantesBD.TABLE_LIKES_CONTACT_NUMERO_LIKES + ")" +
                " FROM " + ConstantesBD.TABLE_LIKES_CONTACT + " WHERE " +
                ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contacto.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }
}
