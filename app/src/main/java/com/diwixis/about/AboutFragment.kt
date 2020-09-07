package com.diwixis.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.diwixis.filmlibrary.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tap.setOnClickListener {}
    }
}