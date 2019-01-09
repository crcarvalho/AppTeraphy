package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.time.format.DateTimeFormatter

/**
 * Created by Carlos Carvalho on 09/01/2019.
 */
@Entity
class Consulta{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var psicologo: Usuario? = null
    var paciente: Usuario? = null
    var cpfCliente: String? = null
    var dataConsulta: String? = null
    var endereco: String? = null

    constructor()

    constructor(
        id: Int,
        psicologo: Usuario?,
        paciente: Usuario?,
        cpfCliente: String,
        dataConsulta: String?,
        endereco: String?
    ) {
        this.id = id
        this.psicologo = psicologo
        this.paciente = paciente
        this.cpfCliente = cpfCliente
        this.dataConsulta = dataConsulta
        this.endereco = endereco
    }

    constructor(
        psicologo: Usuario?,
        paciente: Usuario?,
        cpfCliente: String,
        dataConsulta: String?,
        endereco: String?
        ) {
        this.psicologo = psicologo
        this.paciente = paciente
        this.cpfCliente = cpfCliente
        this.dataConsulta = dataConsulta
        this.endereco = endereco
    }


}