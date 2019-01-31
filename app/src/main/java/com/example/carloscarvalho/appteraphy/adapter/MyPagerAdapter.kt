package com.example.carloscarvalho.appteraphy.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.carloscarvalho.appteraphy.fragments.ConsultasFragment
import com.example.carloscarvalho.appteraphy.fragments.EventosFragment
import com.example.carloscarvalho.appteraphy.fragments.PsicologosFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ConsultasFragment()
            }
            1 -> {
                PsicologosFragment()
                
            }
            else -> {
                return EventosFragment()
            }
        }
    }
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Consultas"
            1 -> "Psicólogos"
            else -> {
                return "Eventos"
            }
        }
    }
}