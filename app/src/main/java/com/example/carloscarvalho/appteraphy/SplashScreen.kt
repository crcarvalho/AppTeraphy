package com.example.carloscarvalho.appteraphy

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity(){

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        var isFirstLogin = preferences.getBoolean("open_first", true )

        if( isFirstLogin ){
            abrirLogin()
        } else {
            abrirSplash();
        }

    }

    //Irá abrir a proxima tela que é a tela de Login
    private fun abrirLogin(){
        val proximaTela = Intent( this@SplashScreen, LoginActivity::class.java)
        startActivity(proximaTela)
        finish()
    }

    private val TEMPO_SPLASH_SCRREN = 3500L

    //Irá executar a animação de splash
    private fun abrirSplash(){
        val anim = AnimationUtils.loadAnimation(
            this,
            R.anim.animacao_splash
        )
        anim.reset()
        //Pegando o nosso objeto criado no layout
        ivLogoSplash.clearAnimation()
        ivLogoSplash.startAnimation(anim)

        Handler().postDelayed({
            val proximaTela = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(proximaTela)
            finish()
        }, TEMPO_SPLASH_SCRREN)
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}