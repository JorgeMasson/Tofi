package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import masson.reynoso.tofi.databinding.FragmentBibliotecaBinding
import masson.reynoso.tofi.ui.biblioteca.BibliotecaFragment
import masson.reynoso.tofi.ui.perfil.PerfilFragment

class Configuracion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        val imgDevolver = findViewById<ImageView>(R.id.btnBackConf)
        val btnEditarPerfil = findViewById<Button>(R.id.btnEditarPerfil)
        val btnCambiarPerfil = findViewById<Button>(R.id.btnCambiarPerfil)

        btnCambiarPerfil.setOnClickListener {
            val lanzar = Intent(this, ProfilesActivity::class.java)
            startActivity(lanzar)
        }

        btnEditarPerfil.setOnClickListener {
            val lanzar = Intent(this, ConfiguraPerfilActivity::class.java)
            startActivity(lanzar)
        }

    }
}