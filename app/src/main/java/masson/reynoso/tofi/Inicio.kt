package masson.reynoso.tofi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import masson.reynoso.tofi.databinding.ActivityInicioBinding

class Inicio : AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_inicio)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_inicio, R.id.navigation_buscar, R.id.navigation_biblioteca, R.id.navigation_actividades, R.id.navigation_perfil
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val bundle = intent.extras
        var nombre = bundle?.getString("nombre")


    }
}