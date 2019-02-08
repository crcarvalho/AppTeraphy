package com.example.carloscarvalho.appteraphy

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.carloscarvalho.appteraphy.extensions.value
import com.example.carloscarvalho.appteraphy.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_perfil.*

class PerfilActivity : AppCompatActivity() {

    val idUser = FirebaseAuth.getInstance().currentUser!!.uid
    val tabela = FirebaseDatabase.getInstance().getReference("Usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        btCancel.setOnClickListener {
            backPreviousActivity()
        }

        btUpdate.setOnClickListener {

            val builder = AlertDialog.Builder(this@PerfilActivity)
            builder.setTitle(getString(R.string.dialog_title_confirmation))
            builder.setMessage(getString(R.string.dialog_message_confirmation_update_info_user))
            builder.setPositiveButton(getString(R.string.dialog_button_ok)){ dialog, witch ->
                updateFireBase()
            }

            builder.setNeutralButton("Cancel") { dialog, witch ->

            }

            //Constroi o dialog e apresenta ao usuario
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        carregarDadosUsuario()
    }

    private fun carregarDadosUsuario(){

        tabela.child(idUser).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Carregando dados do firebase e preenchendo a tela
                val user = dataSnapshot.getValue(Usuario::class.java)
                inputNome.setText(user?.nome)
                inputSobrenome.setText(user?.sobrenome)
                inputEmail.setText(user?.email)
                inputTelefone.setText(user?.telefone)
                inputCPF.setText(user?.cpf)
                inputDtNascimento.setText(user?.dtNascimento)
                inputEndereco.setText(user?.endereco)
                inputCidade.setText(user?.cidade)
                inputEstado.setText(user?.estado)
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
        val usuario = Usuario(inputNome.value(),inputSobrenome.value(),inputTelefone.value(),inputCPF.value(),
            inputDtNascimento.value(),inputCidade.value(),inputEndereco.value(),inputEstado.value(),inputEspecialidades.value())

        tabela
            .child(idUser)
            .setValue(usuario)
            .addOnCompleteListener {
                if( it.isSuccessful ){
                    val telaAnterior = Intent()
                    setResult(Activity.RESULT_OK, telaAnterior)
                    finish()
                    Toast.makeText(this,getString(R.string.message_success_data_update), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,getString(R.string.message_error_data_not_updated), Toast.LENGTH_SHORT).show()
                }
            }


    }
}
