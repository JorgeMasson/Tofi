package masson.reynoso.tofi

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import masson.reynoso.tofi.databinding.ActivityProfilesBinding
import masson.reynoso.tofi.ui.inicio.InicioFragment


class ProfilesActivity : AppCompatActivity() {
    var binding: ActivityProfilesBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var dialog: Dialog
    private lateinit var user: User
    private lateinit var uid: String

    private lateinit var dbref: DatabaseReference
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        binding = ActivityProfilesBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //////OBTENER DATOS DE FIREBASE DATABASE//////

        userArrayList = arrayListOf<User>()



        dbref = FirebaseDatabase.getInstance().getReference("Users")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)

                        val usuario = Firebase.auth.currentUser
                        if (usuario != null) {
                            // User is signed in

                            auth = FirebaseAuth.getInstance()
                            uid = auth.currentUser?.uid.toString()
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users")

                            if (uid.isNotEmpty()) {
                                getUserData()
                            }
                        }
                    }
                } else {
                    loadPerfiles()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }


    //Metodo para navegar entre perfiles
    private fun navigate(profileName: Array<String>, position: Int) {


        if (position == 0) {
            val lanzar = Intent(this, ConfiguraPerfilActivity::class.java)
            startActivity(lanzar)
        }
            else if(position == 1){
            val lanzar = Intent(this, InicioFragment::class.java)
                startActivity(lanzar)
            }
        else {
            Toast.makeText(
                this@ProfilesActivity,
                "You Clicked on " + profileName[position],
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    private fun loadPerfiles() {
        val profileName = arrayOf("Add Profile");
        val profileImage = intArrayOf(R.drawable.agregarperfil);
        val gridAdapter = GridAdapter(this@ProfilesActivity, profileName, profileImage);


        binding!!.userList.adapter = gridAdapter
        binding!!.userList.onItemClickListener =

            AdapterView.OnItemClickListener { parent, view, position, id ->

                navigate(profileName, position)

            }
    }

    private fun getUserData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                val profileName = arrayOf("Add Profile", "${user.name}");
                val profileImage = intArrayOf(R.drawable.agregarperfil, R.drawable.profile_avatar);
                val gridAdapter = GridAdapter(this@ProfilesActivity, profileName, profileImage);


                binding!!.userList.adapter = gridAdapter
                binding!!.userList.onItemClickListener =

                    AdapterView.OnItemClickListener { parent, view, position, id ->

                        navigate(profileName, position)

                    }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}