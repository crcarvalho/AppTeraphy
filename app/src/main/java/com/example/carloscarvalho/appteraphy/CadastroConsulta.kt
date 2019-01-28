package com.example.carloscarvalho.appteraphy

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.carloscarvalho.appteraphy.extensions.value
import com.example.carloscarvalho.appteraphy.model.Consulta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro_consulta.*

class CadastroConsulta : AppCompatActivity() {

    private var consulta: Consulta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_consulta)

        //Recupera valores dos campos quando a activity é acionada pelo botão de atualizar
        val objConsulta = intent.getSerializableExtra("OBJ_CONSULTA")

        //Quando as variaveis estão preenchidas os campos são preenchidos
        if( objConsulta != null ){
            consulta = objConsulta as Consulta
            inputDate.setText(objConsulta.dataConsulta.toString())
            inputPsicologo.setText(objConsulta.psicologo.toString())
        }

        //botão de SALVAR dados inseridos na activity
        btSalvarConsulta.setOnClickListener {
            if( inputDate.text != null && !inputDate.text.isBlank() && !inputPsicologo.text.isBlank() && inputPsicologo.text != null){
                //Toast.makeText(this, "Método não concluido!", Toast.LENGTH_LONG ).show()
                salvarNoFireBase()
            }else{
                Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG ).show()
            }
        }

        //botão de CANCELAR dados inseridos na activity
        btCancelarCadConsulta.setOnClickListener {
            val intent = Intent(this@CadastroConsulta,HomeApplication::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun salvarNoFireBase(){

        val idUser = FirebaseAuth.getInstance().currentUser!!.uid
        val tabela = FirebaseDatabase.getInstance().getReference("Consultas")
        val id = if ( consulta != null ) consulta?.id.toString() else tabela.push().key.toString()
        val consulta = Consulta( id, inputPsicologo.value(), idUser, inputDate.value())

        tabela
            .child(id)
            .setValue(consulta)
            .addOnCompleteListener {
                if( it.isSuccessful ){
                    Toast.makeText(this, "Consulta agendada na data "+ inputDate.value() + " com sucesso!", Toast.LENGTH_LONG ).show()

                    val telaAnterior = Intent()
                    telaAnterior.putExtra("carregarLista", true)
                    setResult(Activity.RESULT_OK, telaAnterior)

                    //Limpa campos
                    inputPsicologo.setText("")
                    inputDate.setText("")

                    finish()
                }else{
                    Toast.makeText(this, "Erro ao criar uma consulta", Toast.LENGTH_LONG ).show()
                }
            }
    }
}
