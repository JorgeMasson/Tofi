package masson.reynoso.tofi

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoveryActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btnChecar = findViewById<Button>(R.id.btnChecar)
        val imgDevolver = findViewById<ImageButton>(R.id.ibRegresar)

        btnChecar.setOnClickListener{
            val et_correo: EditText = findViewById(R.id.inputUsuarioRecover)

            var correo: String = et_correo.text.toString()

            if(!correo.isNullOrBlank()){
                //enviarcorreo
                auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Se envio un correo a $correo",
                                Toast.LENGTH_SHORT).show()
                            val intent: Intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,"Error al enviar el correo",
                                Toast.LENGTH_SHORT).show()
                        }
                    }


            }

            else{
                Toast.makeText(this,"Ingresar correo",
                    Toast.LENGTH_SHORT).show()
            }
        }

        imgDevolver.setOnClickListener{
            val lanzar = Intent(this, LoginActivity:: class.java)
            startActivity(lanzar)
        }
    }
}