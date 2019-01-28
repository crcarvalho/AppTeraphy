package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Carlos Carvalho on 09/01/2019.
 */
@Entity
class Consulta: Serializable{

    @PrimaryKey(autoGenerate = true)
    var id: String? = null
    var psicologo: String? = null
    var paciente: String? = null
    var cpfCliente: String? = null
    var dataConsulta: String? = null
    var endereco: String? = null

    constructor()

    constructor(
        id: String,
        psicologo: String?,
        paciente: String?,
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
        psicologo: String?,
        paciente: String?,
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

    constructor(id: String?,psicologo: String?, paciente: String?, dataConsulta: String?) {
        this.id = id
        this.psicologo = psicologo
        this.paciente = paciente
        this.dataConsulta = dataConsulta
    }


}