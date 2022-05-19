package masson.reynoso.tofi.ui.perfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import masson.reynoso.tofi.*
import masson.reynoso.tofi.ProfilesActivity.Companion.perfilActivo
import masson.reynoso.tofi.databinding.FragmentPerfilBinding


class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    var iconos = ArrayList<Int>()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        cargaIconos()

        view.nombrePerfil.setText(perfilActivo?.nombre)
        view.iconoperfil.setImageResource(iconos.get(perfilActivo!!.icono))

        view.btnConfig.setOnClickListener {
            var intent = Intent(view.context, Configuracion::class.java)
            view.context.startActivity(intent)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun cargaIconos(){
        iconos.add(R.drawable.icono1)
        iconos.add(R.drawable.icono2)
        iconos.add(R.drawable.icono3)
        iconos.add(R.drawable.icono4)
        iconos.add(R.drawable.icono5)
        iconos.add(R.drawable.icono6)
        iconos.add(R.drawable.icono7)
        iconos.add(R.drawable.icono8)
        iconos.add(R.drawable.agregar)
    }

}