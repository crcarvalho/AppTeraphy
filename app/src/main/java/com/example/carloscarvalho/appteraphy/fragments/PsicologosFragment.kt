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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //dentro do listOF vai o retorno JSON de um array de pokemons ou podemos inserir psicologos na lista
        rvPsicologos.adapter = ListaAdapterPsicologos(listOf(Usuario("Daniele",
            "D'Anunciação","dani@gmail.com","11997976520",
            "37031836832", "112233",
            "15101987", "Psicóloga"),
            Usuario("Paula",
                "Cerqueira","paula@gmail.com","11999995555",
                "33366655521", "112233",
                "18011990", "Psicóloga"),
            Usuario("Maria",
                "Candido","maria@gmail.com","11999995566",
                "55566688812", "112233",
                "1812974", "Psicóloga"),
            Usuario("Gioavana",
                "Antoneli","giovana@gmail.com","11999995577",
                "44477788855", "112233",
                "1011966", "Psicóloga"),
            Usuario("Mayara",
                "Vargas","mayara@gmail.com","11999995588",
                "12365478998", "112233",
                "01051988", "Psicóloga")), activity!!, {})
        rvPsicologos.layoutManager = LinearLayoutManager(activity)
    }
}