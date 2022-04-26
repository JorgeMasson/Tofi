package masson.reynoso.tofi

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class RecoveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        val btnChecar = findViewById<Button>(R.id.btnChecar)
        val imgDevolver = findViewById<ImageButton>(R.id.ibRegresar)

        btnChecar.setOnClickListener{
            val lanzar = Intent(this, RecoveryPassActivity:: class.java)
            startActivity(lanzar)
        }

        imgDevolver.setOnClickListener{
            val lanzar = Intent(this, LoginActivity:: class.java)
            startActivity(lanzar)
        }
    }
}