package com.bol.instantapp.manager

object FakeFirebaseClient {
    val map = mutableMapOf("test1" to false, "test2" to true, "test3" to false)
    fun isEnabled(name: String) = map.getOrDefault(name, false)
}