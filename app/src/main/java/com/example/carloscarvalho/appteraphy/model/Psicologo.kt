package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Carlos Carvalho on 09/01/2019.
 */
@Entity
class Psicologo : Serializable{

    @PrimaryKey(autoGenerate = true)
    var id: String? = null
    var usuario: Usuario? = null
    var especialidades: String? = null
    var cidade: String? = null
    var cep: String? = null

    constructor(usuario: Usuario, especialidades: String, cidade: String, cep: String){
        this.usuario = usuario
        this.especialidades = especialidades
        this.cidade = cidade
        this.cep = cep
    }
}