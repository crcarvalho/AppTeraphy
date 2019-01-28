package com.example.carloscarvalho.appteraphy.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carloscarvalho.appteraphy.CadastroConsulta
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterConsultas
import com.example.carloscarvalho.appteraphy.model.Consulta
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.consulta_item.view.*
import kotlinx.android.synthetic.main.consultas_fragment.*

class ConsultasFragment : Fragment() {

    lateinit var reference: DatabaseReference
    lateinit var fireBaseRecyclerView: FirebaseRecyclerAdapter<Consulta,ListaAdapterConsultas.ViewHolder>

    companion object {

        fun newInstance(): ConsultasFragment {
            return ConsultasFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.consultas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*if( FirebaseDatabase.getInstance() == null){
            //Permite trabalhar offline
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        }*/

        //botao floating para adicionar consulta
        fabAddConsulta.setOnClickListener {
            val intent = Intent(activity, CadastroConsulta::class.java)
            startActivity(intent)
        }

        reference = FirebaseDatabase.getInstance().getReference("Consultas")

        carregarRecyclerView()

    }

    fun carregarRecyclerView(){
        fireBaseRecyclerView = object :FirebaseRecyclerAdapter<Consulta, ListaAdapterConsultas.ViewHolder>(
            Consulta::class.java,
            R.layout.consulta_item,
            ListaAdapterConsultas.ViewHolder::class.java,
            reference // reference.orderByChild("nome")
        ){
            override fun populateViewHolder(
                viewHolder: ListaAdapterConsultas.ViewHolder?,
                consulta: Consulta?,
                position: Int
            ) {
                viewHolder?.itemView?.etConsultaData?.setText(consulta?.dataConsulta.toString())
                viewHolder?.itemView?.etConsultaPsicologo?.setText(consulta?.psicologo.toString())

                //Botão excluir da lista de consultas
                viewHolder!!.itemView.btConsultaItemExcluir.setOnClickListener {

                    reference.child(consulta?.id.toString()).removeValue()

                    Toast.makeText(activity,"Consulta excluída com sucesso!",Toast.LENGTH_SHORT ).show()
                }

                viewHolder!!.itemView.setOnClickListener {
                    val intent = Intent(viewHolder.itemView.context, CadastroConsulta::class.java)
                    intent.putExtra("OBJ_CONSULTA", consulta)
                    viewHolder.itemView.context.startActivity(intent)
                }
            }
        }

        rvConsultas.adapter = fireBaseRecyclerView
        rvConsultas.layoutManager = LinearLayoutManager(activity)
    }
}