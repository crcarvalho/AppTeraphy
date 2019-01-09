package com.example.carloscarvalho.appteraphy.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.model.Usuario
import kotlinx.android.synthetic.main.psicologo_item.view.*

/**
 * Created by Carlos Carvalho on 09/01/2019.
 */
class ListaAdapterPsicologos(
        val psicologos: List<Usuario>,
        val context: Context,
        val listener: (Usuario) -> Unit):
        RecyclerView.Adapter<ListaAdapterPsicologos.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.psicologo_item, parent, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return psicologos.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.psicologo_item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val psicologo = psicologos[position]
        holder?.let {
            it.bindView(psicologo, listener)
        }
    }

    class ViewHolder( itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView( psicologo: Usuario, listener: (Usuario) -> Unit) = with(itemView){

            //var nomePsicologo = itemView.findViewById<TextView>(R.id.tvNamePsicologo)
            //nomePsicologo.text = psicologo.nome + " " + psicologo.sobrenome
            //cidade.text = psicologo.cidade
            itemView.tvNamePsicologo.text = psicologo.nome + " " + psicologo.sobrenome
            //itemView.tvCidade.text = psicologo.cidade
            //itemView.tvEspecialidade.text = psicologo.especialidades
            itemView.tvEmail.text = psicologo.email
            itemView.tvCelular.text = psicologo.telefone

            //itemView.tvNa.text = psicologo.nome + " " + psicologo.sobrenome

            //Para apresentar foto do psicologo futuramente
            /*Picasso.get()
                    .load(pokemon?.sprites?.frontDefault)
                    .placeholder(R.drawable.searching)
                    .error(R.drawable.notfound)
                    .into(ivPokemon)*/

            setOnClickListener({ listener(psicologo)})
        }
    }


}