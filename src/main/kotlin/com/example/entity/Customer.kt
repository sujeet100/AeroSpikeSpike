package com.example.entity

import com.aerospike.client.Record

data class Customer(val id: Int, val name: String, val age: Int, val address: String)

fun Record.toCustomer(): Customer {
    return Customer(
        this.getInt("id"),
        this.getString("name"),
        this.getInt("age"),
        this.getString("address")
    )
}
