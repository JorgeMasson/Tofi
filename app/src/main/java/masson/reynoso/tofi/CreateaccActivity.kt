package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CreateaccActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createacc)


        val btnRegistro = findViewById<Button>(R.id.btnRegistrar)
        val imgDevolver = findViewById<ImageButton>(R.id.ibRegresar)
        imgDevolver.setOnClickListener{
            val lanzar = Intent(this, LoginActivity:: class.java)
            startActivity(lanzar)
        }
        btnRegistro.setOnClickListener{
            val lanzar = Intent(this, ConfiguraPerfilActivity:: class.java)
            startActivity(lanzar)
        }
        }

    }
