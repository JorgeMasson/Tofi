package masson.reynoso.tofi

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.ConfiguraPerfilActivity.Companion.perfil
import masson.reynoso.tofi.CreateaccActivity.Companion.user
import masson.reynoso.tofi.R.color.amarilloOscuro

class BienvenidaActivity : AppCompatActivity() {

    var temas = ArrayList<Tema>()
    var perfiles = ArrayList<Perfil>()
    var temasSeleccionados = ArrayList<String>()
    var adaptador: TemaAdapter? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val fs = Firebase.firestore
        auth = Firebase.auth

        val btnListo = findViewById<Button>(R.id.btnListo)
        val gridTemas = findViewById<GridView>(R.id.grid_temas)

        cargaTemas()

        adaptador = TemaAdapter(this, temas)
        gridTemas.adapter = adaptador

        temasSeleccionados = adaptador!!.obtenerTemasSeleccionados()

        btnListo.setOnClickListener {
            var email = user!!.email
            var contraseña = user!!.contraseña
            var usuario = user?.usuario
            var edad = perfil!!.edad
            var nombre = perfil!!.nombre
            var icono = perfil!!.icono

            var pf = Perfil(nombre,edad,icono,temasSeleccionados)

            if(!perfiles.contains(pf)){
                perfiles.add(pf)
            }

            val user = hashMapOf(
                "usuario" to usuario,
                "email" to email,
                "contraseña" to contraseña,
                "perfiles" to perfiles
            )

            fs.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        ContentValues.TAG,
                        "DocumentSnapshot added with ID: ${documentReference.id}"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            registrarFireBase(email, contraseña)

            val lanzar = Intent(this, Inicio:: class.java)
            startActivity(lanzar)

        }




    }

    private fun registrarFireBase(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "Se creo correctamente el usuario")
                    val user = auth.currentUser
                    val lanzar = Intent(this, ConfiguraPerfilActivity:: class.java)
                    startActivity(lanzar)
                } else {
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun cargaTemas() {
        temas.add(Tema("Animales", R.drawable.perro, R.drawable.perro_select))
        temas.add(Tema("Deportes", R.drawable.basket, R.drawable.basket_select))
        temas.add(Tema("Magia", R.drawable.varita, R.drawable.varita_select))
        temas.add(Tema("Videojuegos", R.drawable.videojuego, R.drawable.videojuego_select))
        temas.add(Tema("Musica", R.drawable.musica, R.drawable.musica_select))
        temas.add(Tema("Ciencia ficcion", R.drawable.cohete, R.drawable.cohete_select))

    }


    class TemaAdapter : BaseAdapter {
        var temas = ArrayList<Tema>()
        var temasSeleccionados = ArrayList<String>()
        var context: Context? = null

        constructor(context: Context?, temas: ArrayList<Tema>) {
            this.context = context
            this.temas = temas
        }

        override fun getCount(): Int {
            return temas.size
        }

        override fun getItem(p0: Int): Any {
            return temas[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        @SuppressLint("ResourceAsColor")
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var tema = temas[p0]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.tema, null)

            var fondo: LinearLayout = vista.findViewById(R.id.fondo)
            var titulo: TextView = vista.findViewById(R.id.tv_tema)
            var icono: ImageView = vista.findViewById(R.id.iv_tema)

            titulo.setText(tema.titulo)
            icono.setImageResource(tema.icono)

            icono.setOnClickListener {
                fondo.setBackgroundResource(R.drawable.degradado_dorado)
                titulo.setTextColor(amarilloOscuro)
                icono.setImageResource(tema.iconoseleccionado)

                if(!temasSeleccionados.contains(tema.titulo)){
                    temasSeleccionados.add(tema.titulo)
                }

            }
            return vista
        }



        fun obtenerTemasSeleccionados(): ArrayList<String>{
            return temasSeleccionados
        }

    }




}