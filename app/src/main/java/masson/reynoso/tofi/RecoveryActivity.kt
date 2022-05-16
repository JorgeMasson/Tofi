package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RecoveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        val btnChecar = findViewById<Button>(R.id.btnChecar)

        btnChecar.setOnClickListener{
            val lanzar = Intent(this, RecoveryPassActivity:: class.java)
            startActivity(lanzar)
        }
    }
}