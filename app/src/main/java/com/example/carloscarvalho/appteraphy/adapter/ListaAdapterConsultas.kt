package com.example.carloscarvalho.appteraphy.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.model.Consulta
import kotlinx.android.synthetic.main.consulta_item.view.*

/**
 * Created by Carlos Carvalho on 09/01/2019.
 */
class ListaAdapterConsultas(
    val consultas: List<Consulta>,
    val context: Context,
    val listener: (Consulta) -> Unit):
    RecyclerView.Adapter<ListaAdapterConsultas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.consulta_item, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return consultas.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.consulta_item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val consulta = consultas[position]
        holder?.let {
            it.bindView(consulta, listener)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(consulta: Consulta, listener: (Consulta) -> Unit) = with(itemView) {

            itemView.tvConsultaData.text = consulta.dataConsulta.toString()
            var nomeCompleto = consulta.psicologo?.nome + " " + consulta.psicologo?.sobrenome
            itemView.tvConsultaPsicologo.text = nomeCompleto

            setOnClickListener({ listener(consulta) })
        }
    }
}
