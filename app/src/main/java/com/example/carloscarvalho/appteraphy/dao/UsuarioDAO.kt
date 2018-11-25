package com.example.carloscarvalho.appteraphy.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.carloscarvalho.appteraphy.model.Usuario

/**
 * Created by Carlos Carvalho on 25/11/2018.
 */

@Dao
interface UsuarioDAO{

    @Insert
    fun inserir(user: Usuario)

    @Update
    fun atualizar(user: Usuario)

    @Delete
    fun excluir(user: Usuario)

    @Query("SELECT * FROM Usuario")
    fun lerUsuarios(): LiveData<List<Usuario>>

    @Query("SELECT * FROM Usuario WHERE cpf = :cpf")
    fun buscaPorCPF(cpf: String): LiveData<List<Usuario>>

    @Query("SELECT * FROM Usuario WHERE email = :email")
    fun buscaPorEmail(email: String): LiveData<List<Usuario>>

}