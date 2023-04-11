package com.bigtoapp.numberstesttask.numbers.presentation

import android.view.View
import com.bigtoapp.numberstesttask.main.presentation.Handle
import com.bigtoapp.numberstesttask.main.presentation.UiFeature
import com.bigtoapp.numberstesttask.numbers.domain.NumbersResult
import kotlinx.coroutines.Job

abstract class NumbersFeature(
    private val communications: NumbersCommunications,
    private val numbersResultMapper: NumbersResult.Mapper<Unit>
) : UiFeature, suspend () -> NumbersResult {

    override fun handle(handle: Handle): Job {
        communications.showProgress(View.VISIBLE)
        return handle.handle(this) { result ->
            communications.showProgress(View.GONE)
            showUi(result)
        }
    }

    protected fun showUi(result: NumbersResult) = result.map(numbersResultMapper)
}