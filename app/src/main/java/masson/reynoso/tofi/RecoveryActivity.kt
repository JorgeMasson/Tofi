package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecoveryActivity : AppCompatActivity() {

    val fs = Firebase.firestore

    companion object {
        var correo: String? = null
        var usuario: String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        val btnChecar = findViewById<Button>(R.id.btnChecar)

        btnChecar.setOnClickListener{
            val et_usuario: EditText = findViewById(R.id.txtUsuario)

            usuario = et_usuario.text.toString()

            if(!usuario.isNullOrBlank()){
                checarCorreo()
            }else{
                Toast.makeText(this,"Ingresar datos",Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun checarCorreo(){
        fs.collection("users").whereEqualTo("usuario", usuario).get().addOnSuccessListener { documents ->
            for(document in documents){
                correo = document.get("email") as String?
            }

            val lanzar = Intent(this, RecoveryPassActivity:: class.java)
            startActivity(lanzar)
        }
            .addOnFailureListener{
                Toast.makeText(this,"El usuario no es correcto!",Toast.LENGTH_SHORT).show()
            }
    }


}