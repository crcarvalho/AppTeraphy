package com.example.carloscarvalho.appteraphy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.example.carloscarvalho.appteraphy.adapter.MyPagerAdapter
import com.facebook.*
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_application.*
import android.view.MenuInflater
import android.view.MenuItem


class HomeApplication : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_application)


        //A classe MyPagerAdapter é responsavel por acionar o fragment correto dependendo da numero do método getItem
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        //ACESSA A VIEWPAGER LOCALIZADA NA ACTIVITY "activity_home_application"
        viewpager_main.adapter = fragmentAdapter
        //Acessar a TAB LAYOUT localizada na activity "activity_home_application
        tabs_main.setupWithViewPager(viewpager_main)



    }

    private fun logout(){
        //Recuepra a instancia do Firebase
        mAuth = FirebaseAuth.getInstance()

        //Desloga usuario do firebase
        mAuth.signOut()

        val intent = Intent(this@HomeApplication, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.action_logout) {
            logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
