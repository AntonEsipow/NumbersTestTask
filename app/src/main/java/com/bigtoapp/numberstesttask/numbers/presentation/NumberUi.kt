package com.bigtoapp.numberstesttask.numbers.presentation

import android.widget.TextView

data class NumberUi(
    private val id: String,
    private val fact: String
) : Mapper<Boolean, NumberUi> {
    fun map(head: TextView, subtitle: TextView) {
        head.text = id
        subtitle.text = fact
    }

    override fun map(source: NumberUi) = source.id == id
}