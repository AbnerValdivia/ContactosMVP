package com.example.aplicacionejemplo.vistafragment;

import android.widget.Adapter;

import com.example.aplicacionejemplo.adaptador.ContactoAdaptador;
import com.example.aplicacionejemplo.pojo.Contacto;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    void generarLinearLayoutVertical();
    ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos);
    void inicializarAdaptadorRV(ContactoAdaptador adaptador);

}
