package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Carlos Carvalho on 11/01/2019.
 */
@Entity
class Evento{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var nome: String? = null
    var dtEvento: String? = null
    var descricao: String? = null

    constructor()

    constructor(nome: String?, dtEvento: String?, descricao: String?) {
        this.nome = nome
        this.dtEvento = dtEvento
        this.descricao = descricao
    }

    constructor(id: Int, nome: String?, dtEvento: String?, descricao: String?) {
        this.id = id
        this.nome = nome
        this.dtEvento = dtEvento
        this.descricao = descricao
    }
}