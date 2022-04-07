package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


public class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton1 = findViewById<Button>(R.id.btnPrueba)

        boton1.setOnClickListener{
            val lanzar = Intent(this, LoginActivity:: class.java)
            startActivity(lanzar)
        }
    }
}