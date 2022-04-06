package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreateaccActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createacc)


        val btnRegistro = findViewById<Button>(R.id.btnRegistrar)

        btnRegistro.setOnClickListener{
            val lanzar = Intent(this, ConfiguraPerfilActivity:: class.java)
            startActivity(lanzar)
        }
        }

    }
