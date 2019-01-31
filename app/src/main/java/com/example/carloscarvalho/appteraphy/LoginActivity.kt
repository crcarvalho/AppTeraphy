package com.example.carloscarvalho.appteraphy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.carloscarvalho.appteraphy.extensions.value
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private lateinit var mAuth: FirebaseAuth
    private val newUserRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Carrega função do botão de login do facebook
        carregarFacebookButton()

        //Recupera o sharedPreferences
        val sharedPreferences = getSharedPreferences("appteraphy", Context.MODE_PRIVATE);

        //Recupera a instancia do firebase
        mAuth = FirebaseAuth.getInstance()

        //Verifica se o currentUser é diferente de nulo, caso seja significa que o usuario já logou no app
        //Se o usuario clicou no checkbox para manter conectado vai para proxima activity
        if( mAuth.currentUser != null && sharedPreferences.getBoolean("MANTER_CONECTADO", false) ){
            //Direciona o usuario logado para o home da aplicação
            goToHomeApplication()
        }

        //Ações do botão de LOGIN
        //O botão de login utiliza os dados digitados nos campos de login e senha para acessar o
        // usuario cadastrado no firebase
        buttonLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                inputEmailLogin.value(),
                inputSenha.value()
            ).addOnCompleteListener {
                if( it.isSuccessful ){
                    //Criando uma instancia do sharedpreferences para EDIÇÃO
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("MANTER_CONECTADO", cbConectado.isChecked)
                    editor.putString("USUARIO", inputEmailLogin.text.toString())
                    //Mais correto utilizar APPLY do que COMMIT pois o APPLY o android identifica o melhor
                    // momento de comitar sem travar o APP
                    editor.apply()

                    goToHomeApplication()
                }else{
                    showMessage(it.exception?.message.toString())
                }
            }
        }

        //Função do botão CADASTRAR é chamar a Activity de cadastro
        buttonCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivityForResult(intent, newUserRequestCode)
        }



    }

    /*
        @author: Carlos Carvalho
        @date: 11/12/2018
        @description: Direciona o usuario para tela principal da aplicação
     */
    private fun goToHomeApplication(){
        val intent = Intent(this@LoginActivity,HomeApplication::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }


    /*
        @author: Carlos Carvalho
        @date: 11/12/2018
        @description: Carrega as funções do botão login com facebook
     */

    private fun carregarFacebookButton(){
        var buttonLoginFacebook = findViewById<Button>(R.id.buttonLoginFacebook)

        buttonLoginFacebook.setOnClickListener{
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d( "LoginActivity", "Facebook token: " + loginResult.accessToken.token)
                        startActivity(Intent( applicationContext, HomeApplication::class.java))
                    }

                    override fun onCancel(){
                        Log.d( "LoginActivity", "Facebook onError.")
                    }

                    override fun onError( error: FacebookException){
                        Log.d( "LoginActivity", "Facebook onError.")
                    }
                }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        //Preenche os campos de login e senha caso o usuário tenha se cadastrado nesse momento
        if( requestCode == newUserRequestCode && resultCode == Activity.RESULT_OK ) {
            inputEmailLogin.setText(intent.getStringExtra("email"))
            inputSenha.setText(intent.getStringExtra("senha"))
        }

    }

    private fun showMessage(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}
