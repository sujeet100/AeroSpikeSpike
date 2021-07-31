package com.example.repository

import com.aerospike.client.Key
import com.aerospike.client.exp.Exp.*
import com.aerospike.client.policy.Policy
import com.aerospike.client.policy.QueryPolicy
import com.aerospike.client.query.Statement
import com.example.entity.Customer
import com.example.entity.toCustomer
import com.example.model.CustomerRecord
import com.example.repository.ReactorClient.client
import javax.inject.Singleton

@Singleton
/**
 * Create following set in aerospike
 * test.customers with following bins
 * PK, id, name, age, address
 * insert into test.customers(PK, id, name, age, address) values (1, 1, "Sujit", 32, "Pune")
 * insert into test.customers(PK, id, name, age, address) values (2, 2, "Shailesh", 32, "Mumbai")
 * insert into test.customers(PK, id, name, age, address) values (3, 3, "John", 22, "London")
 * insert into test.customers(PK, id, name, age, address) values (4, 4, "Jane", 12, "London")
 */
class CustomerRepository {
    fun getCustomersFromCity(city: String): List<Customer> {
        val stmt = Statement().apply {
            namespace = CustomerRecord.nameSpace
            setName = CustomerRecord.setName
        }

        val queryPolicy = QueryPolicy().apply {
            filterExp = build(
                or(
                    gt(bin(CustomerRecord.age.name, CustomerRecord.age.type), `val`(20)),
                    eq(bin(CustomerRecord.address.name, CustomerRecord.address.type), `val`(city))
                )
            )
        }

        return client.query(queryPolicy, stmt).toList().map { it.record.toCustomer() }
    }

    fun getCustomerById(id: Int): Customer {
        return client.get(Policy(), Key(CustomerRecord.nameSpace, CustomerRecord.setName, id)).toCustomer()
    }
}
