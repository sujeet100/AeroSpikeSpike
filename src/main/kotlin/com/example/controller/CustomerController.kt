package com.example.controller

import com.example.entity.Customer
import com.example.service.CustomerService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class CustomerController(private val customerService: CustomerService) {
    @Get("/customers")
    fun getCustomers(): List<Customer> {
        return customerService.getCustomers("Mumbai")
    }

    @Get("/customers/{id}")
    suspend fun getCustomers(id: Int): Customer {
        return customerService.getCustomerById(id)
    }
}
