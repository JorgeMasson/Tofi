package masson.reynoso.tofi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnInicio)
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        val btnOlvidarContraseña = findViewById<TextView>(R.id.txtOlvidaPass)

        btnLogin.setOnClickListener{
            val lanzar = Intent(this, ProfilesActivity:: class.java)
            startActivity(lanzar)
        }

        btnRegistro.setOnClickListener{
            val lanzar = Intent(this, ConfiguraPerfilActivity:: class.java)
            startActivity(lanzar)
        }

        btnOlvidarContraseña.setOnClickListener{
            val lanzar = Intent(this, RecoveryActivity:: class.java)
            startActivity(lanzar)
        }

    }

}