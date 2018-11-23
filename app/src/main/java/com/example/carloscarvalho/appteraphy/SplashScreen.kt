package com.example.carloscarvalho.appteraphy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import java.util.*

class SplashScreen : AppCompatActivity(){

   /* private val SPLASH_DISPLAY_LENGTH: Long = 3000

    private lateinit var mRandom: Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //Executando o método que iniciará nossa animação
        carregar()
    }

    private fun carregar() {
        val anim = AnimationUtils.loadAnimation(
            this,
            R.anim.animacao_splash
        )
        anim.reset()
        //Pegando o nosso objeto criado no layout
        val iv = findViewById<View>(R.id.splash) as ImageView
        if (iv != null) {
            iv!!.clearAnimation()
            iv!!.startAnimation(anim)
        }
        // Initialize a new Random instance
        mRandom = Random()

        // Initialize the handler instance
        mHandler = Handler()

        mRunnable = Runnable {
            val intent = Intent(
                this,
                LoginActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(intent)
            this.finish()
            mHandler.postDelayed(
                mRunnable, // Runnable
                SPLASH_DISPLAY_LENGTH // Delay in milliseconds
            )
        }
    }

    public override fun onDestroy() {

        if (mHandler != null) {
            mHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }*/

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

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}