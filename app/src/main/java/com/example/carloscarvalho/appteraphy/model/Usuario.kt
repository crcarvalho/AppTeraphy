package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Carlos Carvalho on 25/11/2018.
 */
@Entity
class Usuario{

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var nome: String? = null
    var sobrenome: String? = null
    var email: String? = null
    var telefone: String? = null
    var cpf: String? = null
    var senha: String? = null
    var dtNascimento: String? = null
    var tipo: String? = null


    constructor(
        nome: String,
        sobrenome: String,
        email: String,
        telefone: String,
        cpf: String,
        senha: String,
        dtNascimento: String,
        tipo: String
    ) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.email = email
        this.telefone = telefone
        this.cpf = cpf
        this.senha = senha
        this.dtNascimento = dtNascimento
        this.tipo = tipo
    }

    constructor()
}