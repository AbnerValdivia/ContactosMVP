package com.example.aplicacionejemplo.adaptador;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacionejemplo.db.ConstructorContactos;
import com.example.aplicacionejemplo.pojo.Contacto;
import com.example.aplicacionejemplo.DetalleContacto;
import com.example.aplicacionejemplo.R;

import java.util.ArrayList;

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder>{
    ArrayList<Contacto> contactos;
    Activity activity;

    public ContactoAdaptador(ArrayList<Contacto> contactos, Activity activity){
        this.contactos = contactos;
        this.activity = activity;
    }

    @NonNull
    //Infla el layout y lo pasa al viewholder para que obtenga los views(elementos)
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    //Asocia cada elemento de la lista con sus view
    @Override
    public void onBindViewHolder(@NonNull final ContactoViewHolder contactoViewHolder, int position) {
        final Contacto contacto = contactos.get(position);
        contactoViewHolder.fotoCV.setImageResource(contacto.getFoto());
        contactoViewHolder.nombreCV.setText(contacto.getNombre());
        contactoViewHolder.telefonoCV.setText(contacto.getTelefono());
        contactoViewHolder.likeCV.setText(contacto.getLikes() + " likes");

        contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"Diste like a "+contacto.getNombre(),Toast.LENGTH_LONG).show();

                ConstructorContactos constructorContactos = new ConstructorContactos(activity);
                constructorContactos.darLikeContacto(contacto);
                contactoViewHolder.likeCV.setText(constructorContactos.obtenerLikesContactoC(contacto) + " "+ "Likes");
            }
        });

        contactoViewHolder.fotoCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,contacto.getNombre(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity, DetalleContacto.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("telefono",contacto.getTelefono());
                intent.putExtra("email",contacto.getEmail());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista de contactos
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoCV;
        private TextView nombreCV;
        private TextView telefonoCV;
        private ImageButton btnLike;
        private TextView likeCV;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoCV = itemView.findViewById(R.id.imgFoto);
            nombreCV = itemView.findViewById(R.id.tvNombreCV);
            telefonoCV = itemView.findViewById(R.id.tvTelefonoCV);
            btnLike = itemView.findViewById(R.id.btnLike);
            likeCV = itemView.findViewById(R.id.tvLikes);
        }
    }
}
