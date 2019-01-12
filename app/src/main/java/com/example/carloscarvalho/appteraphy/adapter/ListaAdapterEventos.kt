package com.example.carloscarvalho.appteraphy.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.model.Evento
import kotlinx.android.synthetic.main.consulta_item.view.*
import kotlinx.android.synthetic.main.eventos_item.view.*

/**
 * Created by Carlos Carvalho on 09/01/2019.
 */
class ListaAdapterEventos(
    val eventos: List<Evento>,
    val context: Context,
    val listener: (Evento) -> Unit):
    RecyclerView.Adapter<ListaAdapterEventos.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.eventos_item, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.eventos_item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val evento = eventos[position]
        holder?.let {
            it.bindView(evento, listener)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(evento: Evento, listener: (Evento) -> Unit) = with(itemView) {
            itemView.tvEventoNome.text = evento.nome
            itemView.tvEventoDescricao.text = evento.descricao
            itemView.tvEventoData.text = evento.dtEvento

            setOnClickListener({ listener(evento) })
        }
    }

}