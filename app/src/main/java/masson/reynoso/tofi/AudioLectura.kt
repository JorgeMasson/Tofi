package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import masson.reynoso.tofi.ui.inicio.InicioFragment

class AudioLectura : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_lectura)

        val imgDevolver = findViewById<ImageView>(R.id.btnBackAudio)

        imgDevolver.setOnClickListener {
            val lanzar = Intent(this, InicioFragment::class.java)
            startActivity(lanzar)
        }
    }
}