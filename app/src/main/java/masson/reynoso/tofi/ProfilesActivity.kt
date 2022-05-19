package masson.reynoso.tofi


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import masson.reynoso.tofi.ConfiguraPerfilActivity.Companion.iconos
import masson.reynoso.tofi.LoginActivity.Companion.email
import masson.reynoso.tofi.ui.inicio.InicioFragment
import java.util.HashMap
import kotlinx.android.synthetic.main.activity_profiles.*

class ProfilesActivity : AppCompatActivity() {

    val fs = Firebase.firestore
    var pfs = ArrayList<Perfil>()
    var adaptador: PerfilAdapter? = null

    companion object{
        var perfilActivo: Perfil? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)

        val gridPerfiles = findViewById<GridView>(R.id.grid_perfiles)

        fs.collection("users").whereEqualTo("email", email).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var perfiles = document.get("perfiles")

                    if (perfiles != null) {
                        for (perfil in perfiles as ArrayList<Any>) {
                            perfil as HashMap<String, Any>
                            var nombre = perfil.get("nombre")
                            var icono = perfil.get("icono").toString()
                            var edad = perfil.get("edad").toString()
                            var temas = perfil.get("temas")
                            var libros = perfil.get("libros")

                            pfs.add(
                                Perfil(
                                    nombre as String?,
                                    edad.toInt(),
                                    icono.toInt(),
                                    temas as ArrayList<String>?,
                                    libros as ArrayList<Libro>?
                                )
                            )


                        }

                        pfs.add(Perfil("Agregar perfil",0,8,null,null))

                        adaptador = PerfilAdapter(this, pfs)
                        gridPerfiles.adapter = adaptador
                    }
                }
            }

    }

        class PerfilAdapter : BaseAdapter {
            var perfiles = ArrayList<Perfil>()
            var context: Context? = null
            var iconos = ArrayList<Int>()
            var pf: Perfil? = null
            var libros= ArrayList<Libro>()

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
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var vista = inflator.inflate(R.layout.perfil, null)

                var nombre: TextView = vista.findViewById(R.id.tv_nombre)
                var icono: ImageView = vista.findViewById(R.id.iv_icono)

                cargaIconos()


                nombre.setText(perfil.nombre)
                icono.setImageResource(iconos.get(perfil.icono))

                if(perfil.icono == 8){
                    var fondo: LinearLayout = vista.findViewById(R.id.fondo)
                    fondo.setBackgroundResource(R.drawable.degradado_morado)
                }

                icono.setOnClickListener {
                    pf = Perfil(perfil.nombre,perfil.edad,perfil.icono,perfil.temas,perfil.libros)
                    perfilActivo = pf
                    if(perfil.icono == 8){
                        var intent = Intent(context, ConfiguraPerfilActivity::class.java)
                        intent.putExtra("email",email)
                        intent.putExtra("email",email)
                        context!!.startActivity(intent)
                    } else{
                        var intent = Intent(context, Inicio::class.java)
                        intent.putExtra("nombre",perfil.nombre)
                        context!!.startActivity(intent)
                    }
                }


                return vista
            }
            fun cargaIconos(){
                iconos.add(R.drawable.icono1)
                iconos.add(R.drawable.icono2)
                iconos.add(R.drawable.icono3)
                iconos.add(R.drawable.icono4)
                iconos.add(R.drawable.icono5)
                iconos.add(R.drawable.icono6)
                iconos.add(R.drawable.icono7)
                iconos.add(R.drawable.icono8)
                iconos.add(R.drawable.agregar)
            }

            fun perfilSeleccionado(): Perfil? {
                return pf
            }

        }






}
