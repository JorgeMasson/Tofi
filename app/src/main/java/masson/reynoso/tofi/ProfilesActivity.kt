package masson.reynoso.tofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import masson.reynoso.tofi.databinding.ActivityProfilesBinding

class ProfilesActivity : AppCompatActivity() {
    var binding: ActivityProfilesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilesBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        val profileName = arrayOf("Add Profile");
        val profileImage = intArrayOf(R.drawable.agregarperfil);
        val gridAdapter = GridAdapter(this@ProfilesActivity, profileName, profileImage);


        binding!!.gridView.adapter = gridAdapter
        binding!!.gridView.onItemClickListener =

            AdapterView.OnItemClickListener { parent, view, position, id ->

                if (position == 0) {
                    val lanzar = Intent(this, ConfiguraPerfilActivity::class.java)
                    startActivity(lanzar)
                } else {
                    Toast.makeText(this@ProfilesActivity, "You Clicked on " + profileName[0], Toast.LENGTH_SHORT).show()
                }
            }
    }
}