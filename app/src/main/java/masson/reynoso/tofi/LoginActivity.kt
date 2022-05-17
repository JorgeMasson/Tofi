package masson.reynoso.tofi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    var usuarios = ArrayList<User>()

    companion object {
        var email: String? = null
    }


    private lateinit var auth: FirebaseAuth
    private lateinit var mPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val btnLogin = findViewById<Button>(R.id.btnInicio)
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        val btnOlvidarContraseña = findViewById<TextView>(R.id.txtOlvidaPass)

        val mName = findViewById<EditText>(R.id.txtUsuario)
        val mCheckBox = findViewById<CheckBox>(R.id.btnRecordarUser)

        val sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener{
            validaSesion()
        }

        btnRegistro.setOnClickListener{
            val lanzar = Intent(this, CreateaccActivity:: class.java)
            startActivity(lanzar)
        }

        btnOlvidarContraseña.setOnClickListener{
            val lanzar = Intent(this, RecoveryActivity:: class.java)
            startActivity(lanzar)
        }

    }

    private fun validaSesion() {
        val et_correo: EditText = findViewById(R.id.txtUsuario)
        val et_contra: EditText = findViewById(R.id.txtContraseña)

        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()

        if(!correo.isNullOrBlank() && !contra.isNullOrBlank()){
            ingresaFirebase(correo,contra)
            email = correo
        }else{
            Toast.makeText(this,"Ingresar datos",Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresaFirebase(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val lanzar = Intent(this, ProfilesActivity:: class.java)
                    startActivity(lanzar)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }


}