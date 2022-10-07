package com.bigtoapp.numberstesttask.details.presentation

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bigtoapp.numberstesttask.R
import com.bigtoapp.numberstesttask.main.presentation.BaseFragment

class NumberDetailsFragment: BaseFragment<NumberDetailsViewModel>() {

    override val viewModelClass = NumberDetailsViewModel::class.java
    override val layoutId = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = viewModel.read()
        view.findViewById<TextView>(R.id.detailsTextView).text = value
    }
}