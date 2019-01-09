package com.example.carloscarvalho.appteraphy.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            Usuario("Carlos", "Carvalho", "carlos@teste.com", "11997977759","34265901824", "123456", "07101985", "psicologo"),
            Usuario("Pablo", "Andrade", "pabloe@teste.com", "11775558477","12365498778", "123456", "15022000", "paciente"),
            "12365498778", "22/01/2019", "Av. Faria Lima, 1990 - São Paulo-SP"),
            Consulta(2,
                Usuario("Carlos", "Carvalho", "carlos@teste.com", "11997977759","34265901824", "123456", "07101985", "psicologo"),
                Usuario("Pablo", "Andrade", "pabloe@teste.com", "11775558477","12365498778", "123456", "15022000", "paciente"),
                "12365498778", "29/01/2019", "Av. Faria Lima, 1990 - São Paulo-SP"),
            Consulta(3,
                Usuario("Carlos", "Carvalho", "carlos@teste.com", "11997977759","34265901824", "123456", "07101985", "psicologo"),
                Usuario("Pablo", "Andrade", "pabloe@teste.com", "11775558477","12365498778", "123456", "15022000", "paciente"),
                "12365498778", "06/02/2019", "Av. Faria Lima, 1990 - São Paulo-SP")), activity!!, {})
        rvConsultas.layoutManager = LinearLayoutManager(activity)
    }


}