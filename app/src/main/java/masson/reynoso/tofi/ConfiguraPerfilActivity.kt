package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConfiguraPerfilActivity : AppCompatActivity() {

    var countIconos = 0
    var count = 0

    companion object {
        var iconos = ArrayList<Int>()
        var perfil: Perfil? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configura_perfil)


        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        val btnIzquierdo = findViewById<ImageView>(R.id.btnIzquierdo)
        val btnDerecho = findViewById<ImageView>(R.id.btnDerecho)
        val btnMenos = findViewById<ImageView>(R.id.btnDecrement)
        val btnMas = findViewById<ImageView>(R.id.btnIncrement)
        val edad = findViewById<TextView>(R.id.txtEdad)
        val et_nombre = findViewById<EditText>(R.id.txtNombre)
        val iv_icono = findViewById<ImageView>(R.id.ivIcono)

        cargaIconos()

        iv_icono.setImageResource(iconos.get(0))

        btnDerecho.setOnClickListener{
            if(countIconos == 7){
                countIconos = 0
                iv_icono.setImageResource(iconos.get(countIconos))
            }else {
                countIconos = countIconos + 1
                iv_icono.setImageResource(iconos.get(countIconos))
            }
        }

        btnIzquierdo.setOnClickListener{
            if(count == 7){
                count = 0
                iv_icono.setImageResource(iconos.get(count))
            }else {
                count = count + 1
                iv_icono.setImageResource(iconos.get(count))
            }
        }

        btnMenos.setOnClickListener{
            if(count == 0){

            }else {
                count = count - 1
                var txtEdad = (count).toString()
                edad.setText(txtEdad)
            }
        }

        btnMas.setOnClickListener{
            count = count + 1
            var txtEdad = (count).toString()
            edad.setText(txtEdad)
        }

        btnSiguiente.setOnClickListener{

            perfil = Perfil(et_nombre.text.toString(),count,countIconos,null)

            val lanzar = Intent(this, BienvenidaActivity:: class.java)
            startActivity(lanzar)
        }

        val imgDevolver = findViewById<ImageView>(R.id.btnBackConfP)

        imgDevolver.setOnClickListener {
            val lanzar = Intent(this, CreateaccActivity::class.java)
            startActivity(lanzar)
        }
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
    }
}