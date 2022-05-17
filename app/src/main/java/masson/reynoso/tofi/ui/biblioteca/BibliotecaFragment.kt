package masson.reynoso.tofi.ui.biblioteca

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.*
import masson.reynoso.tofi.databinding.FragmentBibliotecaBinding
import masson.reynoso.tofi.ui.inicio.InicioFragment
import masson.reynoso.tofi.ui.inicio.InicioFragment.Companion.nombrePerfil
import java.util.HashMap

class BibliotecaFragment : Fragment() {

    private var _binding: FragmentBibliotecaBinding? = null
    var adaptador: BibliotecaFragment.LibroAdapter? = null
    val fs = Firebase.firestore

    companion object{
        var librosBiblioteca = ArrayList<Libro>()
    }

    var first = true
    var nombre = nombrePerfil



    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { val bibliotecaViewModel = ViewModelProvider(this).get(BibliotecaViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentBibliotecaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (first) {
            first = false
        }

        fs.collection("users").whereEqualTo("email", LoginActivity.email).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var perfiles = document.get("perfiles")

                    for (perfil in perfiles as ArrayList<Any>) {
                        perfil as HashMap<String, Any>
                        var nombrepf = perfil.get("nombre")
                        var libros = perfil.get("libros")

                        if("nunu".equals(nombrepf.toString())){
                            for (libro in libros as ArrayList<Any>) {
                                libro as HashMap<String, Any>
                                var titulo = libro.get("titulo")
                                var descripcion = libro.get("descripcion")
                                var paginas = libro.get("paginas").toString()
                                var portada = libro.get("portada").toString()
                                var categorias = libro.get("categorias")
                                var autor = libro.get("autor")

                                var libro = Libro(
                                    titulo as String,
                                    descripcion as String,
                                    autor as String,
                                    paginas.toInt(),
                                    portada.toInt(),
                                    categorias as ArrayList<String>)

                                if(!librosBiblioteca.contains(libro)){
                                    librosBiblioteca.add(libro)
                                }

                            }

                            adaptador = LibroAdapter(root.context, librosBiblioteca)
                            val table: GridView = root.findViewById(R.id.grid_biblioteca)
                            table.adapter = adaptador
                        }
                    }
                }
            }



        return root
    }

    class LibroAdapter : BaseAdapter {
        var libros = ArrayList<Libro>()
        var context: Context? = null
        var portadas = ArrayList<Int>()

        constructor(context: Context, libros: ArrayList<Libro>) {
            this.context = context
            this.libros = libros
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var libro = libros[p0]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.libro_biblioteca, null)

            var portada: ImageView = vista.findViewById(R.id.iv_portada_cuento)
            var titulo: TextView = vista.findViewById(R.id.tv_titulo_cuento)

            portadas()
            titulo.setText(libro.titulo)
            portada.setImageResource(portadas.get(libro.portada))

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