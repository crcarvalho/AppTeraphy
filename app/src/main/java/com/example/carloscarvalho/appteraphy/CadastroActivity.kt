package com.example.carloscarvalho.appteraphy

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.carloscarvalho.appteraphy.dao.BancoDeDados
import com.example.carloscarvalho.appteraphy.extensions.value
import com.example.carloscarvalho.appteraphy.model.Usuario
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*
import android.os.AsyncTask
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CadastroActivity : AppCompatActivity() {

    private var usuario: Usuario? = null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        mAuth = FirebaseAuth.getInstance()
        buttonCadastrar.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                inputEmail.value(),
                inputSenhaCadastro.value()
            ).addOnCompleteListener {
                if( it.isSuccessful ){
                    saveInRealTimeDatabase()
                } else {
                    this.showMessage(it.exception?.message.toString());
                }
            }
        }
    }

    private fun saveInRealTimeDatabase() {
        usuario = Usuario( inputNome.value(), inputSobrenome.value(), inputEmail.value(), inputTelefone.value(),
            inputCPF.value(), inputSenhaCadastro.value(), inputDtNascimento.value(),
            inputSelectTipo.selectedItem.toString())
        FirebaseDatabase.getInstance().getReference("Usuarios")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(usuario)
            .addOnCompleteListener {
                if( it.isSuccessful ){
                    this.showMessage("Usuário "+ inputNome.value() + " criado com sucesso!");
                    val telaAnterior = Intent()
                    telaAnterior.putExtra("email", inputEmail.value())
                    telaAnterior.putExtra("senha", inputSenhaCadastro.value())
                    setResult(Activity.RESULT_OK, telaAnterior)
                    finish()
                }else{
                    this.showMessage("Erro ao criar o usuário")
                }
            }
    }

    private fun showMessage(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private inner class InsertAsyncTask internal constructor(appDatabase: BancoDeDados) : AsyncTask<Usuario, Void, String>() {
        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Usuario): String {
            db.usuarioDAO().inserir(params[0])
            return ""
        }
    }
}
