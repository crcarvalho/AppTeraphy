package com.example.carloscarvalho.appteraphy.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.CadastroEventoActivity
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterEventos
import com.example.carloscarvalho.appteraphy.model.Evento
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.eventos_fragment.*

import kotlinx.android.synthetic.main.eventos_item.view.*


class EventosFragment : Fragment() {

    lateinit var reference: DatabaseReference
    lateinit var fireBaseRecyclerView: FirebaseRecyclerAdapter<Evento, ListaAdapterEventos.ViewHolder>

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

        reference = FirebaseDatabase.getInstance().getReference("Eventos")

        //botao floating para adicionar consulta
        fabAddEvento.setOnClickListener {
            val intent = Intent(activity, CadastroEventoActivity::class.java)
            startActivity(intent)
        }

        carregarRecyclerView()

    }

    fun carregarRecyclerView(){
        fireBaseRecyclerView = object : FirebaseRecyclerAdapter<Evento, ListaAdapterEventos.ViewHolder> (
            Evento::class.java,
            R.layout.eventos_item,
            ListaAdapterEventos.ViewHolder::class.java,
            reference
        ){
            override fun populateViewHolder(
                viewHolder: ListaAdapterEventos.ViewHolder?,
                evento: Evento?,
                position: Int
            ) {
                viewHolder?.itemView?.tvEventoNome?.setText(evento?.nome.toString())
                viewHolder?.itemView?.tvEventoData?.setText(evento?.dtEvento.toString())
                viewHolder?.itemView?.tvEventoDescricao?.setText(evento?.descricao.toString())
            }
        }

        rvEventos.adapter = fireBaseRecyclerView
        rvEventos.layoutManager = LinearLayoutManager(activity)
    }
}