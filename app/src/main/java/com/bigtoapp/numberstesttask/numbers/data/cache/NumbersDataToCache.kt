package com.bigtoapp.numberstesttask.numbers.data.cache

import com.bigtoapp.numberstesttask.numbers.data.NumberData

class NumbersDataToCache: NumberData.Mapper<NumberCache> {

    override fun map(id: String, fact: String) = NumberCache(id, fact, System.currentTimeMillis())
}