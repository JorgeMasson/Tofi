package masson.reynoso.tofi.ui.buscar

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_buscar.view.*
import masson.reynoso.tofi.*
import masson.reynoso.tofi.databinding.FragmentBuscarBinding
import masson.reynoso.tofi.ui.inicio.InicioFragment

class BuscarFragment : Fragment() {

    private var _binding: FragmentBuscarBinding? = null

    var adaptador: LibroAdapter? = null

        var categorias = ArrayList<Categoria>()
        var first = true
        val fs = Firebase.firestore

    companion object{
        var busqueda: String? = null
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val buscarViewModel =
            ViewModelProvider(this).get(BuscarViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_buscar, container, false)

        if (first) {
            categorias()
            first = false
        }

       view.btnBuscar.setOnClickListener{
           busqueda = view.ev_buscar.text.toString()
           var intent = Intent(view.context, BusquedaActivity::class.java)
           view.context.startActivity(intent)
       }



        (activity as AppCompatActivity).supportActionBar?.hide()

        adaptador = LibroAdapter(view.context, categorias)
        val table: GridView = view.findViewById(R.id.grid_busqueda)

        table.adapter = adaptador

        return view
    }

    fun categorias() {
        categorias.add(Categoria("Animales",R.drawable.animales))
        categorias.add(Categoria("Aventura",R.drawable.aventura))
        categorias.add(Categoria("Ciencia ficcion",R.drawable.cienciaficcion))
        categorias.add(Categoria("Fantasia",R.drawable.magia))
        categorias.add(Categoria("Clasico",R.drawable.clasico))
        categorias.add(Categoria("Novela",R.drawable.novela))
        categorias.add(Categoria("Misterio",R.drawable.misterio))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class LibroAdapter : BaseAdapter {
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
}