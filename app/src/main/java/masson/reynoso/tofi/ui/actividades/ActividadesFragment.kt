package masson.reynoso.tofi.ui.actividades

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.*
import masson.reynoso.tofi.databinding.FragmentActividadesBinding
import masson.reynoso.tofi.ui.biblioteca.BibliotecaFragment
import masson.reynoso.tofi.ui.buscar.BuscarFragment
import java.util.HashMap

class ActividadesFragment : Fragment() {

    private var _binding: FragmentActividadesBinding? = null
    private val binding get() = _binding!!
    var adaptador: LibroAdapter? = null
    var adaptadorc: CategoriaAdapter? = null
    val fs = Firebase.firestore
    var categorias = ArrayList<Categoria>()
    var librosBiblioteca = ArrayList<Libro>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actividadesViewModel =
            ViewModelProvider(this).get(ActividadesViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.hide()
        categorias()

        _binding = FragmentActividadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fs.collection("users").whereEqualTo("email", LoginActivity.email).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var perfiles = document.get("perfiles")

                    for (perfil in perfiles as ArrayList<Any>) {
                        perfil as HashMap<String, Any>
                        var nombrepf = perfil.get("nombre")
                        var libros = perfil.get("libros")

                        if(ProfilesActivity.perfilActivo?.nombre.equals(nombrepf.toString())){

                            if(libros != null){
                                for (libro in libros  as ArrayList<Any>){
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
                            }

                            adaptador = LibroAdapter(root.context, librosBiblioteca)
                            val table: GridView = root.findViewById(R.id.grid_historias)
                            table.adapter = adaptador
                        }
                    }
                }
            }

        adaptadorc = CategoriaAdapter(root.context, categorias)
        val tablec: GridView = root.findViewById(R.id.grid_categorias)
        tablec.adapter = adaptadorc


        return root
    }

    fun categorias() {
        categorias.add(Categoria("Decisiones",R.drawable.misterio))
        categorias.add(Categoria("Pronunciacion",R.drawable.animales))
        categorias.add(Categoria("Memoria",R.drawable.novela))
        categorias.add(Categoria("Escritura",R.drawable.cienciaficcion))
        categorias.add(Categoria("Direcciones",R.drawable.magia))
        categorias.add(Categoria("Deletreo",R.drawable.aventura))
        categorias.add(Categoria("Figuras",R.drawable.clasico))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class CategoriaAdapter : BaseAdapter {
        var categorias = ArrayList<Categoria>()
        var context: Context? = null

        constructor(context: Context, categorias: ArrayList<Categoria>) {
            this.context = context
            this.categorias = categorias
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var categoria = categorias[p0]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.categorias, null)

            var icono: ImageView = vista.findViewById(R.id.iv_categoria)
            var titulo: TextView = vista.findViewById(R.id.tv_categoria)

            titulo.setText(categoria.titulo)
            icono.setImageResource(categoria.portada)

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