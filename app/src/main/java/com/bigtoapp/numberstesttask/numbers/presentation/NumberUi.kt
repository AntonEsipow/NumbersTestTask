package com.bigtoapp.numberstesttask.numbers.presentation

import android.widget.TextView

data class NumberUi(private val id: String, private val fact: String) : Mapper<Boolean, NumberUi> {

    fun <T: Any> map(mapper: Mapper<T>): T = mapper.map(id, fact)

    interface Mapper<T: Any> {
        fun map(id: String, fact: String): T
    }
    override fun map(source: NumberUi) = source.id == id

}

class DetailsUi : NumberUi.Mapper<String> {
    override fun map(id: String, fact: String): String = "$id\n\n$fact"
}

class ListItemUi(
    private val head: TextView,
    private val subTitle: TextView
) : NumberUi.Mapper<Unit> {

    override fun map(id: String, fact: String) {
        head.text = id
        subTitle.text = fact
    }
}