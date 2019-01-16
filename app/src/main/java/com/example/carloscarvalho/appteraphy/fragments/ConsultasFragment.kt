package com.example.carloscarvalho.appteraphy.fragments

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carloscarvalho.appteraphy.CadastroConsulta
import com.example.carloscarvalho.appteraphy.HomeApplication
import com.example.carloscarvalho.appteraphy.R
import com.example.carloscarvalho.appteraphy.adapter.ListaAdapterConsultas
import com.example.carloscarvalho.appteraphy.model.Consulta
import com.example.carloscarvalho.appteraphy.model.Usuario
import kotlinx.android.synthetic.main.consultas_fragment.*

class ConsultasFragment : Fragment() {

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

        //dentro do listOF vai o retorno JSON de um array de consultas ou podemos inserir consultas na lista
        rvConsultas.adapter = ListaAdapterConsultas(listOf(Consulta(1,
            "Carlos Dantas",
            "Pablo Andrade",
            "12365498778", "22/01/2019", "Av. Faria Lima, 1990 - São Paulo-SP"),
            Consulta(2,
                "Carlos Dantas",
                "Pablo Andrade",
                "12365498778", "29/01/2019", "Av. Faria Lima, 1990 - São Paulo-SP"),
            Consulta(3,
                "Carlos Dantas",
                "Pablo Andrade",
                "12365498778", "06/02/2019", "Av. Faria Lima, 1990 - São Paulo-SP")), activity!!, {})
        rvConsultas.layoutManager = LinearLayoutManager(activity)


        //botao floating para adicionar consulta
        fabAddConsulta.setOnClickListener {
            val intent = Intent(activity,CadastroConsulta::class.java)
            startActivity(intent)

        }

    }


}