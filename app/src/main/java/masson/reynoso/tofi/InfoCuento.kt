package masson.reynoso.tofi

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_info_cuento.*
import masson.reynoso.tofi.ProfilesActivity.Companion.perfilActivo
import masson.reynoso.tofi.ui.inicio.InicioFragment
import java.util.HashMap

class InfoCuento : AppCompatActivity() {

    var portadas = ArrayList<Int>()
    var adaptador: CategoriasAdapter? = null

    companion object{
        var librosAgregados = ArrayList<Libro>()
        var pfs = ArrayList<Perfil>()
    }

    val fs = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_cuento)

        val bundle = intent.extras
        var portada = 0
        var paginas = 0
        var id= -1
        var titulo=""
        var autor=""
        var categorias: ArrayList<String>? = null
        var descripcion =""
        var nombre =""

        portadas()

        if(bundle != null){
            portada= bundle.getInt("portada")
            paginas= bundle.getInt("paginas")
            titulo = bundle.getString("titulo").toString()
            autor = bundle.getString("autor").toString()
            descripcion = bundle.getString("descripcion").toString()
            categorias = bundle.getStringArrayList("categorias")!!
            nombre = bundle.getString("nombre").toString()

            img_info_cuento.setImageResource(portadas.get(portada))
            tv_titulo_cuento.setText(titulo)
            tv_autor.setText(autor)
            tv_numero_paginas.setText("$paginas paginas")
            tv_descripción_cuento.setText(descripcion)
            id= bundle.getInt("pos")
        }



        btnFav.setOnClickListener {

            var libro = Libro(titulo,descripcion,autor,paginas,portada, categorias!!)

            if(!librosAgregados.contains(libro)){
                librosAgregados.add(libro)
            }


            fs.collection("users").whereEqualTo("email", LoginActivity.email).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        var perfiles = document.get("perfiles")

                        for (perfil in perfiles as ArrayList<Any>) {
                            perfil as HashMap<String, Any>
                            var nombre = perfil.get("nombre")
                            var icono = perfil.get("icono").toString()
                            var edad = perfil.get("edad").toString()
                            var temas = perfil.get("temas") as ArrayList<String>?
                            var libros = perfil.get("libros")

                            var pf = Perfil(nombre as String?, edad.toInt(), icono.toInt(), temas ,null)

                            if(libros != null){
                                for (libro in libros as ArrayList<Libro>) {
                                    libro as HashMap<String, Any>
                                    var titulo = libro.get("titulo")
                                    var autor = libro.get("autor")
                                    var portada = libro.get("portada").toString()
                                    var paginas = libro.get("paginas").toString()
                                    var descripcion = libro.get("descripcion")
                                    var categorias = libro.get("categorias")

                                    var lb = Libro(titulo as String,
                                        descripcion as String,
                                        autor as String, paginas.toInt(), portada.toInt(), categorias!! as ArrayList<String>
                                    )

                                    if(!librosAgregados.contains(lb)){
                                        librosAgregados.add(lb)
                                    }
                                }
                            }

                            if((nombre as String?).equals(pf.nombre)){
                                Toast.makeText(this,"Entro"+ pf.nombre,Toast.LENGTH_SHORT).show()
                                pf.libros = librosAgregados
                                pfs.add(pf)
                                fs.collection("users").document(document.id).update("perfiles",pfs)
                            }else{
                            if(!pfs.contains(pf)){
                                pfs.add(pf)
                            }
                        }
                    }
                }
            }
        }

        adaptador = CategoriasAdapter(this, categorias!!)
        val gridCategorias = findViewById<GridView>(R.id.grid_tags)
        gridCategorias.adapter = adaptador

    }

    fun portadas(){
        portadas.add(R.drawable.alicia)
        portadas.add(R.drawable.caperucita)
        portadas.add(R.drawable.castillos)
        portadas.add(R.drawable.gigante)
        portadas.add(R.drawable.jardin)
        portadas.add(R.drawable.peterpan)
        portadas.add(R.drawable.rapunzel)
    }


    class CategoriasAdapter : BaseAdapter {
        var categorias = ArrayList<String>()
        var context: Context?= null

        constructor(context: Context, categorias: ArrayList<String>){
            this.context = context
            this.categorias = categorias
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var categoria = categorias[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.etiqueta_categoria,null)

            var titulo: TextView = vista.findViewById(R.id.tv_categoria)
            titulo.setText(categoria)

            return vista
        }

        override fun getItem(position: Int): Any {
            return categorias[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return categorias.size
        }
    }


}