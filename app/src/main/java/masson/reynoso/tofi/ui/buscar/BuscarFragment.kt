package masson.reynoso.tofi.ui.buscar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import masson.reynoso.tofi.R
import masson.reynoso.tofi.databinding.FragmentBuscarBinding
import masson.reynoso.tofi.ui.inicio.InicioFragment

class BuscarFragment : Fragment() {

    private var _binding: FragmentBuscarBinding? = null

    var adaptador: LibroAdapter? = null

        var libros = ArrayList<String>()
        var first = true


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

        _binding = FragmentBuscarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (first) {
            fillLibros()
            first = false
        }

        (activity as AppCompatActivity).supportActionBar?.hide()

        adaptador = LibroAdapter(root.context, libros)
        val table: GridView = root.findViewById(R.id.grid_busqueda)

        table.adapter = adaptador

        return root
    }

    fun fillLibros() {
        libros.add("1")
        libros.add("1")
        libros.add("1")
        libros.add("1")
        libros.add("1")
        libros.add("1")
        libros.add("1")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class LibroAdapter : BaseAdapter {
        var libros = ArrayList<String>()
        var context: Context? = null

        constructor(context: Context, libros: ArrayList<String>) {
            this.context = context
            this.libros = libros
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var libro = libros[p0]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.categorias, null)

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
    }
}