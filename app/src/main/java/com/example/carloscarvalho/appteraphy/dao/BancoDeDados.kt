package com.example.carloscarvalho.appteraphy.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.carloscarvalho.appteraphy.model.Usuario

/**
 * Created by Carlos Carvalho on 25/11/2018.
 */
@Database(entities = arrayOf(Usuario::class), version = 1)
abstract class BancoDeDados: RoomDatabase(){

    abstract fun usuarioDAO(): UsuarioDAO


    companion object {
        var INSTANCE: BancoDeDados? = null

        fun getDatabase( context: Context):BancoDeDados? {
            if( INSTANCE == null ){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    BancoDeDados::class.java,
                    "teraphydb").build()
            }

            return INSTANCE
        }
    }


}