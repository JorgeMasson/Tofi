package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ConfiguraPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configura_perfil)


        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        val imgDevolver = findViewById<ImageButton>(R.id.ibRegresar)
        imgDevolver.setOnClickListener{
            val lanzar = Intent(this, CreateaccActivity:: class.java)
            startActivity(lanzar)
        }
        btnSiguiente.setOnClickListener{
            val lanzar = Intent(this, BienvenidaActivity:: class.java)
            startActivity(lanzar)
        }
    }
}