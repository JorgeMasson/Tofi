package masson.reynoso.tofi

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import masson.reynoso.tofi.databinding.ActivityConfiguraPerfilBinding

class ConfiguraPerfilActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth;
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var dialog: Dialog
    var sum = 0;
    var res = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configura_perfil)
        //OBTENCION DE DATOS
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val imgDevolver = findViewById<ImageButton>(R.id.ibRegresar)


        //GUARDAR DATOS PERFIL
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        btnGuardar.setOnClickListener{
            showProgressBar()
            val firstName = findViewById<EditText>(R.id.etNombre)
            val age = findViewById<TextView>(R.id.tvEdad)
            var nombre: String = firstName.text.toString()
            var edad: String = age.text.toString()

            val user = User(nombre, edad)

            if(uid != null){


                databaseReference.child(uid).setValue(user).addOnCompleteListener() {
//                    Toast.makeText(this@ConfiguraPerfilActivity, "Usuario " + nombre + " Edad " + edad, Toast.LENGTH_SHORT).show()

                    if(it.isSuccessful){
                        uploadProfilePic()
                        val lanzar = Intent(this, BienvenidaActivity:: class.java)
                        startActivity(lanzar)
                    }else{
                        hideProgressBar()
                        Toast.makeText(this@ConfiguraPerfilActivity, "Error al salvar perfil", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }



        //EVENT CLICK LISTENER
        imgDevolver.setOnClickListener{
            val lanzar = Intent(this, CreateaccActivity:: class.java)
            startActivity(lanzar)
        }

//        btnSiguiente.setOnClickListener{
//            val lanzar = Intent(this, BienvenidaActivity:: class.java)
//            startActivity(lanzar)
//        }

        //SUMA Y RESTA DE EDAD
        var b1 = findViewById<ImageButton>(R.id.btnRes)
        var b2 = findViewById<ImageButton>(R.id.btnSum)
        var tv = findViewById<TextView>(R.id.tvEdad)


        b1.setOnClickListener{
            val a = tv.text.toString()
            val A = a.toInt()
            if (A > 0){
                sum--
            }
            tv.setText("$sum")
        }

        b2.setOnClickListener{
            sum++
            tv.setText("$sum")
        }
    }

    private fun uploadProfilePic() {

        imageUri = Uri.parse("android.resource://$packageName/${R.drawable.profile_avatar}")

        storageReference = FirebaseStorage.getInstance().getReference("Users/" + auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {

            hideProgressBar()
            Toast.makeText(this@ConfiguraPerfilActivity, "Perfil guardado", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            hideProgressBar()
            Toast.makeText(this@ConfiguraPerfilActivity, "Error al guardar imagen", Toast.LENGTH_SHORT).show()

        }
    }

    private fun showProgressBar(){

        dialog = Dialog(this@ConfiguraPerfilActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }
}