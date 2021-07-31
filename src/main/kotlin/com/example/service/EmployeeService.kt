package com.example.service

import com.example.entity.Address
import com.example.entity.Employee
import com.example.entity.Skill
import javax.inject.Singleton

@Singleton
class EmployeeService {

    fun getEmployee(): List<Employee> {
        return listOf<Employee>(
            Employee(
                "Sujit",
                22,
                Address("Pune", "411028", "MH"),
                listOf(Skill("Scala", 5), Skill("Java", 3))
            ),
            Employee(
                "Kamthe",
                32,
                Address("Mumbai", "400001", "MH"),
                listOf(Skill("Kotlin", 3), Skill("Kafka", 4))
            )

        )
    }
}
