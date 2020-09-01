package com.example.aplicacionejemplo.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.aplicacionejemplo.R;
import com.example.aplicacionejemplo.pojo.Contacto;

import java.util.ArrayList;

public class ConstructorContactos {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorContactos(Context context){
        this.context = context;
    }

    public ArrayList<Contacto> obtenerDatos(){
        BaseDatos db = new BaseDatos(context);
        //insertarTresContactosaBD(db);
        return db.obtenerTodosLosContactos();
    }

    public void insertarTresContactosaBD(BaseDatos db){
        //1
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_CONTACTS_NOMBRE, "Alejandro Valdivia");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_TELEFONO, "123456789");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_EMAIL, "correo@gmail.com");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_FOTO, R.drawable.icons8_morty_smith_48);
        db.insertarContacto(contentValues);

        //2
        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_CONTACTS_NOMBRE, "Anahí Diaz");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_TELEFONO, "7552369820");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_EMAIL, "correoDePedro@gmail.com");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_FOTO, R.drawable.icons8_persona_femenina_240);
        db.insertarContacto(contentValues);

        //3
        contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_CONTACTS_NOMBRE, "Juan Lopez");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_TELEFONO, "6786494529");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_EMAIL, "correodeJuan@gmail.com");
        contentValues.put(ConstantesBD.TABLE_CONTACTS_FOTO, R.drawable.icons8_persona_48);
        db.insertarContacto(contentValues);
    }

    public void darLikeContacto(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_LIKES_CONTACT_ID_CONTACTO, contacto.getId());
        contentValues.put(ConstantesBD.TABLE_LIKES_CONTACT_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }

    public int obtenerLikesContactoC(Contacto contacto){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(contacto);
    }

}
