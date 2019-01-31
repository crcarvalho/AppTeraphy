package com.example.carloscarvalho.appteraphy

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carloscarvalho.appteraphy.extensions.value
import com.example.carloscarvalho.appteraphy.model.Evento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_cadastro_evento.*

class CadastroEventoActivity : AppCompatActivity() {

    private var evento: Evento? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_evento)

        //Recupera valores dos campos quando a activity é acionada pelo botão de atualizar
        val objEvento = intent.getSerializableExtra("OBJ_CONSULTA")

        //Quando as variaveis estão preenchidas os campos são preenchidos
        if (objEvento != null) {
            evento = objEvento as Evento
            inputDateEvento.setText(objEvento.dtEvento.toString())
            inputDescricao.setText(objEvento.descricao.toString())
            inputNomeEvento.setText(objEvento.nome.toString())
        }

        //botão de SALVAR dados inseridos na activity
        btSalvarEvento.setOnClickListener {
            if (inputDateEvento.text != null && !inputDateEvento.text.isBlank() && !inputNomeEvento.text.isBlank() &&
                inputNomeEvento.text != null && inputDescricao.text != null && !inputDescricao.text.isBlank()
            ) {
                //Toast.makeText(this, "Método não concluido!", Toast.LENGTH_LONG ).show()
                salvarNoFireBase()
            } else {
                Toast.makeText(this, getString(R.string.required_fields), Toast.LENGTH_LONG).show()
            }
        }

        //botão de CANCELAR dados inseridos na activity
        btCancelarCadEvento.setOnClickListener {
            val intent = Intent(this@CadastroEventoActivity, HomeApplication::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun salvarNoFireBase() {

        val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        val tabela = FirebaseDatabase.getInstance().getReference("Eventos")
        val id = if (evento != null) evento?.id.toString() else tabela.push().key.toString()

        val evento = Evento(id, inputNomeEvento.value(), inputDateEvento.value(), inputDescricao.value(), idUser)

        tabela
            .child(id)
            .setValue(evento)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Evento cadastrado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()

                    val telaAnterior = Intent()
                    telaAnterior.putExtra("carregarLista", true)
                    setResult(Activity.RESULT_OK, telaAnterior)

                    //Limpa campos
                    inputNomeEvento.setText("")
                    inputDescricao.setText("")
                    inputDateEvento.setText("")

                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.error_create_exam), Toast.LENGTH_LONG).show()
                }
            }
    }
}
