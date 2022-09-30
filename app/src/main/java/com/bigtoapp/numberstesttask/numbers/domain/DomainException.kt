package com.bigtoapp.numberstesttask.numbers.domain

abstract class DomainException: IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException: DomainException()