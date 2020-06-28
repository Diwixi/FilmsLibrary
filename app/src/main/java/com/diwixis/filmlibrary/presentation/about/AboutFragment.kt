package com.diwixis.filmlibrary.presentation.about

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.diwixis.filmlibrary.R
import kotlinx.android.synthetic.main.fragment_about.*

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class AboutFragment : Fragment(R.layout.fragment_about) {

    lateinit var connectivityManager: ConnectivityManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tap.setOnClickListener {

        }


    }
}