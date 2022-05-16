package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tofi = findViewById<ImageView>(R.id.btnPrueba)

        tofi.setOnClickListener{
            val login = Intent(this,LoginActivity::class.java)
            startActivity(login)
        }
    }
} 