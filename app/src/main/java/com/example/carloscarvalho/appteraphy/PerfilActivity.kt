package com.example.carloscarvalho.appteraphy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_perfil.*

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        btCancel.setOnClickListener {
            backPreviousActivity()
        }

        btUpdate.setOnClickListener {
            updateFireBase()
        }
    }

    private fun backPreviousActivity()
    {
        val intent = Intent(this@PerfilActivity, HomeApplication::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun updateFireBase()
    {
        Toast.makeText(this,"Função não implementada", Toast.LENGTH_SHORT).show()
    }
}
