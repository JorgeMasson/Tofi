package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import java.util.*


public class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
//        val boton1 = findViewById<Button>(R.id.btnPrueba)
//
//        boton1.setOnClickListener{
//            val lanzar = Intent(this, LoginActivity:: class.java)
//            startActivity(lanzar)
//        }
    }
}