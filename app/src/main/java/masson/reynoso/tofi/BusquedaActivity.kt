package masson.reynoso.tofi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.ui.buscar.BuscarFragment.Companion.busqueda
import masson.reynoso.tofi.ui.inicio.InicioFragment

class BusquedaActivity : AppCompatActivity() {

    var adaptador: InicioFragment.LibroAdapter? = null

    companion object{
        var libros = ArrayList<Libro>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resultados_busqueda)

        val fs = Firebase.firestore

        fs.collection("libros").whereEqualTo("titulo", busqueda).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var titulo = document.get("titulo")
                    var descripcion = document.get("descripcion")
                    var paginas = document.get("paginas").toString()
                    var portada = document.get("portada").toString()
                    var categorias = document.get("categorias")
                    var autor = document.get("autor")

                    var libro = Libro(
                        titulo as String,
                        descripcion as String,
                        autor as String,
                        paginas.toInt(),
                        portada.toInt(),
                        categorias as ArrayList<String>)

                    if(!InicioFragment.libros.contains(libro)){
                        InicioFragment.libros.add(libro)
                    }

                }
            }

        adaptador = InicioFragment.LibroAdapter(this,libros)
        val table = findViewById<GridView>(R.id.grid_busqueda)
        table.adapter = adaptador

    }

    class LibroAdapter : BaseAdapter {
        var libros = ArrayList<Libro>()
        var context: Context?= null
        var portadas = ArrayList<Int>()

        constructor(context: Context, libros: ArrayList<Libro>){
            this.context = context
            this.libros = libros
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var libro = libros[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.portada_libro,null)

            portadas()

            var portada: ImageView = vista.findViewById(R.id.iv_portada_cuento)
            portada.setImageResource(portadas.get(libro.portada))

            portada.setOnClickListener{
                var intent = Intent(context, InfoCuento::class.java)
                intent.putExtra("titulo", libro.titulo)
                intent.putExtra("portada", libro.portada)
                intent.putExtra("autor", libro.autor)
                intent.putExtra("descripcion", libro.descripcion)
                intent.putExtra("categorias",libro.categorias)
                intent.putExtra("paginas",libro.paginas)
                intent.putExtra("nombre", ProfilesActivity.perfilActivo?.nombre)
                intent.putExtra("pos",p0)
                context!!.startActivity(intent)
            }

            return vista
        }

        override fun getItem(position: Int): Any {
            return libros[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return libros.size
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

    }

}