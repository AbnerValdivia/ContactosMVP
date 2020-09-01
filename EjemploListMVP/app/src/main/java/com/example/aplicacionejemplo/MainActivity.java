package com.example.aplicacionejemplo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.aplicacionejemplo.adaptador.ContactoAdaptador;
import com.example.aplicacionejemplo.adaptador.PageAdapter;
import com.example.aplicacionejemplo.vistafragment.PerfilFragment;
import com.example.aplicacionejemplo.vistafragment.RecyclerViewFragment;
import com.example.aplicacionejemplo.pojo.Contacto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;
    private RecyclerView listaContactos;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    /* Sin usar RecyclerView
    ListView lstContactos;
    ArrayList<String> nombreContacto;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
            tabLayout = findViewById(R.id.tabLayout);
            viewPager = findViewById(R.id.viewPager);

        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }
        setUpViewPager();
        /* Antes de utilizar Fragments
        listaContactos = findViewById(R.id.rvContactos);

        LinearLayoutManager llm =new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaContactos.setLayoutManager(llm);
        InicializarListaContactos();
        InicializarAdaptador();
        */

      /* Sin usar RecyclerView
        nombreContacto = new ArrayList<>();
        for (Contacto contacto: contactos) {
            nombreContacto.add(contacto.getNombre());
        }*/
      /*
        lstContactos = findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombreContacto));

        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DetalleContacto.class);
                intent.putExtra(getResources().getString(R.string.pnombre),contactos.get(i).getNombre());
                intent.putExtra(getResources().getString(R.string.ptelefono),contactos.get(i).getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail),contactos.get(i).getEmail());
                startActivity(intent);
            }
        });
        */

    }
    //Para el uso de fragments
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icons8_contactos_100);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons8_contactos_100muchos);
    }

/*    public void InicializarAdaptador(){
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos,this);
        listaContactos.setAdapter(adaptador);
    }
    public void InicializarListaContactos(){
        contactos = new ArrayList<>();

        contactos.add(new Contacto(R.drawable.icons8_morty_smith_48,"Alejandro Sanchez","12346789","alex@email.com", likes));
        contactos.add(new Contacto(R.drawable.icons8_persona_48,"Pedro Rubio","7552369820","pedro@email.com", likes));
        contactos.add(new Contacto(R.drawable.icons8_persona_femenina_240,"Anahi Salgado","4896325587","anahi@email.com", likes));
        contactos.add(new Contacto(R.drawable.icons8_persona_mareada_40,"Juan Lopez","7681040289","juan@email.com", likes));

    }*/
}
