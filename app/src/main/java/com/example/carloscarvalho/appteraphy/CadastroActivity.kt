package com.example.carloscarvalho.appteraphy

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

class CadastroActivity : AppCompatActivity() {

    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        buttonCadastrar.setOnClickListener {
            val db = BancoDeDados.getDatabase(applicationContext)
            usuario = Usuario(inputNome.value(),inputSobrenome.value(),
                inputEmail.value(),inputTelefone.value(),inputCPF.value(),
                inputSenhaCadastro.value(),inputDtNascimento.value(),
                inputSelectTipo.selectedItem.toString())

            var mensagem: String
            var voltaLogin: Boolean
            if( usuario != null ) {
                InsertAsyncTask(db!!).execute(usuario)
                mensagem = "Usuário cadastrado com sucesso."
                voltaLogin = true
            }
            else {
                mensagem = "Ocorreu um erro ao salvar o usuário."
                voltaLogin = false
            }

            Toast.makeText(this,
                mensagem, Toast.LENGTH_LONG).show()


            if( voltaLogin ) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("email", inputEmail.value())
                intent.putExtra("senha", inputSenhaCadastro.value())
                startActivity(intent)
                finish()
            }
        }
    }

    private inner class InsertAsyncTask internal constructor(appDatabase: BancoDeDados) : AsyncTask<Usuario, Void, String>() {
        private val db: BancoDeDados = appDatabase

        override fun doInBackground(vararg params: Usuario): String {
            db.usuarioDAO().inserir(params[0])
            return ""
        }
    }
}
