package com.example.carloscarvalho.appteraphy.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterPsicologos
import com.example.carloscarvalho.appteraphy.model.Usuario
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.psicologo_item.view.*
import kotlinx.android.synthetic.main.psicologos_fragment.*


class PsicologosFragment : Fragment() {

    lateinit var reference: DatabaseReference
    lateinit var fireBaseRecyclerView: FirebaseRecyclerAdapter<Usuario, ListaAdapterPsicologos.ViewHolder>

    companion object {

        fun newInstance(): PsicologosFragment {
            return PsicologosFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.psicologos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        reference = FirebaseDatabase.getInstance().getReference("Usuarios")

        carregarRecyclerView()

    }

    fun carregarRecyclerView(){
        fireBaseRecyclerView = object : FirebaseRecyclerAdapter<Usuario, ListaAdapterPsicologos.ViewHolder> (
            Usuario::class.java,
            R.layout.psicologo_item,
            ListaAdapterPsicologos.ViewHolder::class.java,
            reference
        ){
            override fun populateViewHolder(
                viewHolder: ListaAdapterPsicologos.ViewHolder?,
                usuario: Usuario?,
                position: Int
            ) {
                viewHolder?.itemView?.tvNamePsicologo?.setText(usuario?.nome +" "+ usuario?.sobrenome)
                viewHolder?.itemView?.tvEmail?.setText(usuario?.email)
                viewHolder?.itemView?.tvCelular?.setText(usuario?.telefone)
            }
        }

        rvPsicologos.adapter = fireBaseRecyclerView
        rvPsicologos.layoutManager = LinearLayoutManager(activity)
    }
}