package com.example.carloscarvalho.appteraphy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.R.attr.versionName
import android.content.pm.PackageInfo
import kotlinx.android.synthetic.main.activity_sobre.*


class SobreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)

        try {
            val pInfo = this.getPackageManager().getPackageInfo(packageName, 0)
            val version = pInfo.versionName
            val textVersao = "Versão número: "+ version
            tvSobreVersao.setText(textVersao)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }
}
