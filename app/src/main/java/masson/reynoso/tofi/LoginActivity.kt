package masson.reynoso.tofi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val btnLogin = findViewById<Button>(R.id.btnInicio)
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)

        //////////////////////////////REMEMBERME////////////////////////////////////

        // Recuperamos el contenido de los textField
        val mName = findViewById<EditText>(R.id.inputUsuarioLogin)
        val mCheckBox = findViewById<CheckBox>(R.id.btnRecordarUser)

        val sp = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)



        ////////////////////////////////////////////////////////////////////////////

        btnLogin.setOnClickListener {
            val lanzar = Intent(this, ProfilesActivity::class.java)
            startActivity(lanzar)

//            valida_ingreso()
        }

        btnRegistro.setOnClickListener {
            val lanzar = Intent(this, CreateaccActivity::class.java)
            startActivity(lanzar)
        }

    }



    private fun valida_ingreso() {
        val et_correo: EditText = findViewById(R.id.inputUsuarioLogin)
        val et_contra: EditText = findViewById(R.id.inputPassLogin)


        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()

        /////////////////////////////----------------REMEMBERME------------------/////////////////////////////


        /////////////////////////////-----------------------------------/////////////////////////////

        if (!correo.isNullOrBlank() && !contra.isNullOrBlank()) {
            //ingresa a la Firebase

            ingresaFirebase(correo, contra)

        } else {
            Toast.makeText(this, "Ingresar datos!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresaFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)

                    val intent: Intent = Intent(this, ProfilesActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
    }


    fun clickTextView(view: View?) {
        val lanzar = Intent(this, RecoveryActivity::class.java)
        startActivity(lanzar)
    }
}