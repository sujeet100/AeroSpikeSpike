package com.example.entity

data class Employee(val name: String, val age: Int, val address: Address, val skills : List<Skill>)

data class Address(val city: String, val pin: String, val state: String)

data class Skill(val skillName: String, val expertise: Int)
