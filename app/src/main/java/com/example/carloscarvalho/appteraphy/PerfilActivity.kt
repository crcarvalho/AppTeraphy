package com.example.carloscarvalho.appteraphy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carloscarvalho.appteraphy.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

        carregarDadosUsuario()
    }


    private fun carregarDadosUsuario(){
        val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        val tabela = FirebaseDatabase.getInstance().getReference("Usuarios")

        tabela.child(idUser).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(Usuario::class.java)
                Toast.makeText(this@PerfilActivity,"Usuário encontrado " + user?.nome + " do id " + idUser, Toast.LENGTH_SHORT).show()

                inputNome.setText(user?.nome)
                inputSobrenome.setText(user?.sobrenome)
                inputEmail.setText(user?.email)
                inputTelefone.setText(user?.telefone)
                inputCPF.setText(user?.cpf)
                inputDtNascimento.setText(user?.dtNascimento)
                //inputEndereco.setText(user?.endereco)
                //inputCidade.setText(user?.cidade)
                //inputEstado.setText(user?.estado)
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@PerfilActivity,getString(R.string.error_server_connect), Toast.LENGTH_SHORT).show()
            }
            
        })
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
