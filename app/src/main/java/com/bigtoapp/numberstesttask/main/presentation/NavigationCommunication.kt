package com.bigtoapp.numberstesttask.main.presentation

import com.bigtoapp.numberstesttask.numbers.presentation.Communication

interface NavigationCommunication {

    interface Observe : Communication.Observe<NavigationStrategy>

    interface Mutate : Communication.Mutate<NavigationStrategy>

    interface Mutable : Observe, Mutate

    class Base : Communication.SingleUi<NavigationStrategy>(), Mutable
}