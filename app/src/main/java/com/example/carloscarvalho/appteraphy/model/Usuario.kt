package com.example.carloscarvalho.appteraphy.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

/**
 * Created by Carlos Carvalho on 25/11/2018.
 */
@Entity
@IgnoreExtraProperties
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
    var cidade: String? = null
    var endereco: String? = null
    var estado: String? = null
    var especialidades: String? = null


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

    constructor(
        nome: String?,
        sobrenome: String?,
        telefone: String?,
        cpf: String?,
        dtNascimento: String?,
        cidade: String?,
        endereco: String?,
        estado: String?,
        especialidades: String?
    ) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.telefone = telefone
        this.cpf = cpf
        this.dtNascimento = dtNascimento
        this.cidade = cidade
        this.endereco = endereco
        this.estado = estado
        this.especialidades = especialidades
    }
}