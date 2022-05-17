package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import masson.reynoso.tofi.ui.inicio.InicioFragment
import masson.reynoso.tofi.ui.perfil.PerfilFragment

class Lectura : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lectura)

        val imgDevolver = findViewById<ImageView>(R.id.btnBackLectura)

        imgDevolver.setOnClickListener {
            val lanzar = Intent(this, InicioFragment::class.java)
            startActivity(lanzar)
        }
    }
}