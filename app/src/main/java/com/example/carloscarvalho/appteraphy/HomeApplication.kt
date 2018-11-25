package com.example.carloscarvalho.appteraphy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.facebook.*
import com.facebook.login.LoginManager

class HomeApplication : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_application)

        var btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener(
            {
                if (AccessToken.getCurrentAccessToken() != null) {
                    GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/permissions/",
                        null,
                        HttpMethod.DELETE,
                        GraphRequest.Callback {
                            AccessToken.setCurrentAccessToken(null)
                            LoginManager.getInstance().logOut()
                        })
                }
            }
        )
    }
}
