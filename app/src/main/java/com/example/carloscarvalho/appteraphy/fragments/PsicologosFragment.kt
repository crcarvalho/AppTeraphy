package com.example.carloscarvalho.appteraphy.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterPsicologos
import com.example.carloscarvalho.appteraphy.model.Usuario
import kotlinx.android.synthetic.main.psicologos_fragment.*


class PsicologosFragment : Fragment() {


    //2
    companion object {

        fun newInstance(): PsicologosFragment {
            return PsicologosFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.psicologos_fragment, container, false)
/*
        //dentro do listOF vai o retorno JSON de um array de pokemons ou podemos inserir psicologos na lista
        rvPsicologos.adapter = ListaAdapterPsicologos(listOf(Usuario("Carlos",
                "Carvalho","carlos@gmail.com","11972847759",
                "34265901824", "112233",
                "07101985", "Psicólogo")), activity.applicationContext, {})
        rvPsicologos.layoutManager = LinearLayoutManager(this)*/
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

}