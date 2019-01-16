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

        btSalvarConsulta.setOnClickListener {
            if( inputDate.text != null && !inputDate.text.isBlank() && !inputPsicologo.text.isBlank() && inputPsicologo.text != null){
                Toast.makeText(this, "Método não concluido!", Toast.LENGTH_LONG ).show()
                //salvarNoFireBase()
            }else{
                Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG ).show()
            }
        }

        btCancelarCadConsulta.setOnClickListener {
            val intent = Intent(this@CadastroConsulta,HomeApplication::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun salvarNoFireBase(){
        consulta = Consulta()

        FirebaseDatabase.getInstance().getReference("Usuarios")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(consulta)
            .addOnCompleteListener {
                if( it.isSuccessful ){
                    Toast.makeText(this, "Consulta agendada na data "+ inputDate.value() + " com sucesso!", Toast.LENGTH_LONG ).show()

                    val telaAnterior = Intent()
                    telaAnterior.putExtra("carregarLista", true)
                    setResult(Activity.RESULT_OK, telaAnterior)
                    finish()
                }else{
                    Toast.makeText(this, "Erro ao criar uma consulta", Toast.LENGTH_LONG ).show()
                }
            }
    }
}
