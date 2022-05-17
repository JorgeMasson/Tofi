package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.RecoveryActivity.Companion.usuario

class RecoveryPassActivity : AppCompatActivity() {

    val fs = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recoverypass)

        val btnReestablecer = findViewById<Button>(R.id.btnReestablecer)
        val txtBienvenido = findViewById<TextView>(R.id.txtBienvenido)

        txtBienvenido.setText("¡Hola, " + usuario +"!")

        btnReestablecer.setOnClickListener{

        }

    }

    private fun validaRegistro() {
        val et_contra: EditText = findViewById(R.id.txtContraseña)
        val et_contra2: EditText = findViewById(R.id.txtConfirmar)

        var contra: String = et_contra.text.toString()
        var contra2: String = et_contra2.text.toString()


        if (!contra.isNullOrBlank() && !contra2.isNullOrBlank()) {
            if(contra == contra2){

                val lanzar = Intent(this, ProfilesActivity:: class.java)
                startActivity(lanzar)
            } else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }




}