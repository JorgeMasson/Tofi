package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RecoveryPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recoverypass)

        val btnReestablecer = findViewById<Button>(R.id.btnReestablecer)

        btnReestablecer.setOnClickListener{
            val lanzar = Intent(this, ProfilesActivity:: class.java)
            startActivity(lanzar)
        }
    }
}