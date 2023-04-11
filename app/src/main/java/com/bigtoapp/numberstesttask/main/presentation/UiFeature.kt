package com.bigtoapp.numberstesttask.main.presentation

import kotlinx.coroutines.Job

interface UiFeature {
    fun handle(handle: Handle): Job
}