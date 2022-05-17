package masson.reynoso.tofi

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CreateaccActivity : AppCompatActivity() {

    companion object {
        var user: User? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createacc)



        val btn_Registrar: Button = findViewById(R.id.btnRegistro)

        btn_Registrar.setOnClickListener {
            validaRegistro()
        }

    }

    private fun validaRegistro() {

        val et_correo: EditText = findViewById(R.id.txtEmail)
        val et_contra: EditText = findViewById(R.id.txtContraseña)
        val et_contra2: EditText = findViewById(R.id.txtConfirmar)
        val et_usuario: EditText = findViewById(R.id.txtUsuario)

        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()
        var contra2: String = et_contra2.text.toString()
        var usuario: String = et_usuario.text.toString()


        if (!correo.isNullOrBlank() && !contra.isNullOrBlank() && !contra2.isNullOrBlank()) {

            if(contra == contra2){
                user = User(usuario,correo,contra,null,)
                val lanzar = Intent(this, ConfiguraPerfilActivity:: class.java)
                startActivity(lanzar)
            } else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
        }
    }



}