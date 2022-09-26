package com.bigtoapp.numberstesttask.numbers.presentation

import android.widget.TextView

data class NumberUi(
    private val id: String,
    private val fact: String
) {
    fun map(head: TextView, subtitle: TextView) {
        head.text = id
        subtitle.text = fact
    }
}