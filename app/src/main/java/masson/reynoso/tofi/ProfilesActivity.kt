package masson.reynoso.tofi


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.arrayMapOf
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.LoginActivity.Companion.email
import java.util.HashMap

class ProfilesActivity : AppCompatActivity() {

    var perfiles = ArrayList<Perfil>()
    val fs = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        cargarPerfiles()

    }


    fun cargarPerfiles(){
        fs.collection("users").whereEqualTo("email",email).get().addOnSuccessListener { documents ->
            for(document in documents){
                var perfiles = document.get("perfiles")
                var correo = document.get("email")
                var contraseña = document.get("contraseña")
                var usuario = document.get("usuario")


                if (perfiles != null) {
                    for(perfil in perfiles as ArrayList<Any>){
                        
                    }
                }
            }
        }
    }


    class PerfilAdapter : BaseAdapter {
        var perfiles = ArrayList<Perfil>()
        var context: Context? = null

        constructor(context: Context?, perfiles: ArrayList<Perfil>) {
            this.context = context
            this.perfiles = perfiles
        }

        override fun getCount(): Int {
            return perfiles.size
        }

        override fun getItem(p0: Int): Any {
            return perfiles[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var perfil = perfiles[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.perfil, null)

            var fondo: LinearLayout = vista.findViewById(R.id.fondo)
            var nombre: TextView = vista.findViewById(R.id.tv_nombre)
            var icono: ImageView = vista.findViewById(R.id.iv_icono)

            nombre.setText(perfil.nombre)
            icono.setImageResource(perfil.icono)

            icono.setOnClickListener {

            }

            return vista
        }

    }
}
