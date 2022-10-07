package com.bigtoapp.numberstesttask.details.data

interface NumberFactDetails {
    interface Save {
        fun save(data: String)
    }

    interface Read {
        fun read(): String
    }

    interface Mutable : Save, Read

    class Base : Mutable {

        private var value = ""

        override fun save(data: String) {
            value = data
        }

        override fun read() = value
    }
}