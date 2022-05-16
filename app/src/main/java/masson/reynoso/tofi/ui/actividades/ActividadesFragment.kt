package masson.reynoso.tofi.ui.actividades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import masson.reynoso.tofi.databinding.FragmentActividadesBinding

class ActividadesFragment : Fragment() {

    private var _binding: FragmentActividadesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actividadesViewModel =
            ViewModelProvider(this).get(ActividadesViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.hide()

        _binding = FragmentActividadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}