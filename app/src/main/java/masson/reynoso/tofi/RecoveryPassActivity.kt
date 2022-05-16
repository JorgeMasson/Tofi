package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class RecoveryPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recoverypass)

        val btnReestablecer = findViewById<Button>(R.id.btnReestablecer)
        val imgDevolver = findViewById<ImageButton>(R.id.ibRegresar)
        imgDevolver.setOnClickListener{
            val lanzar = Intent(this, RecoveryActivity:: class.java)
            startActivity(lanzar)
        }
        btnReestablecer.setOnClickListener{
            val lanzar = Intent(this, ProfilesActivity:: class.java)
            startActivity(lanzar)
        }


    }
}