package com.example.carloscarvalho.appteraphy.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.carloscarvalho.appteraphy.model.Consulta
import com.example.carloscarvalho.appteraphy.model.Usuario
import java.time.format.DateTimeFormatter


/**
 * Created by Carlos Carvalho on 09/01/2019.
 */

@Dao
interface ConsultaDAO{

    @Insert
    fun inserir(consulta: Consulta)

    @Update
    fun atualizar(consulta: Consulta)

    @Delete
    fun excluir(consulta: Consulta)

    @Query("SELECT * FROM Consulta")
    fun lerConsultas(): LiveData<List<Consulta>>

    @Query("SELECT * FROM Consulta WHERE dataConsulta = :dataConsulta AND cpfCliente = :cpfCliente ")
    fun buscaPorDataCPF(dataConsulta: DateTimeFormatter, cpfCliente: String): LiveData<List<Consulta>>

    @Query("SELECT * FROM Consulta WHERE cpfCliente = :cpfCliente")
    fun buscaPorCPF(cpfCliente: String): LiveData<List<Consulta>>

    @Query("SELECT * FROM Consulta WHERE psicologo like '%'+psicologo+'%'")
    fun buscaPorNomePsicologo(psicologo: String): LiveData<List<Consulta>>

}