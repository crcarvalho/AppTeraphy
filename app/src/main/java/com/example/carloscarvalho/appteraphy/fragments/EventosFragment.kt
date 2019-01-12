package com.example.carloscarvalho.appteraphy.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterEventos
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterPsicologos
import com.example.carloscarvalho.appteraphy.model.Evento
import com.example.carloscarvalho.appteraphy.model.Usuario
import kotlinx.android.synthetic.main.eventos_fragment.*
import kotlinx.android.synthetic.main.psicologos_fragment.*


class EventosFragment : Fragment() {

    companion object {
        fun newInstance(): EventosFragment {
            return EventosFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.eventos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //dentro do listOF vai o retorno JSON de um array de pokemons ou podemos inserir psicologos na lista
        rvEventos.adapter = ListaAdapterEventos(listOf(
            Evento("CEP","12/01/2019","A singularidade humana: um diálogo entre a Psicanálise e fenomenologia")
        ), activity!!, {})
        rvPsicologos.layoutManager = LinearLayoutManager(activity)
    }
}