package masson.reynoso.tofi.ui.inicio

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
import masson.reynoso.tofi.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var adaptador: LibroAdapter? = null

    companion object {
        var libros = ArrayList<String>()
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inicioViewModel =
            ViewModelProvider(this).get(InicioViewModel::class.java)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity).supportActionBar?.hide()

        if(first){
            fillLibros()
            first= false
        }

        adaptador = LibroAdapter(root.context, libros)
        val table : GridView = root.findViewById(R.id.grid_catalogo)
        table.adapter = adaptador
        return root
    }

    fun fillLibros(){
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
        var context: Context?= null

        constructor(context: Context, libros: ArrayList<String>){
            this.context = context
            this.libros = libros
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var libro = libros[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.portada_libro,null)

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