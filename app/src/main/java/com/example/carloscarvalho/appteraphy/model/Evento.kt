package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Carlos Carvalho on 11/01/2019.
 */
@Entity
class Evento:Serializable{

    @PrimaryKey(autoGenerate = true)
    var id: String? = null
    var nome: String? = null
    var dtEvento: String? = null
    var descricao: String? = null
    var idUser: String? = null


    constructor()

    constructor(nome: String?, dtEvento: String?, descricao: String?, idUser: String?) {
        this.nome = nome
        this.dtEvento = dtEvento
        this.descricao = descricao
        this.idUser = idUser
    }

    constructor(id: String, nome: String?, dtEvento: String?, descricao: String?, idUser: String?) {
        this.id = id
        this.nome = nome
        this.dtEvento = dtEvento
        this.descricao = descricao
        this.idUser = idUser
    }
}