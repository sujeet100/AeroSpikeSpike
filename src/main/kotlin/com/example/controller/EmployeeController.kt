package com.example.controller

import com.example.service.EmployeeService
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller
class EmployeeController(private val employeeService: EmployeeService, private val objectMapper: ObjectMapper) {

    @Get("/employee")
    fun getCustomers(@QueryValue fields: String): JsonNode {
        val split = fields.split(",")
        val firstLevel = split.filterNot { it.contains(".") }
        val secondLevel = split.filter { it.contains(".") }.map { it.split(".") }
        val employee = employeeService.getEmployee()
        val arrayNode = objectMapper.valueToTree<ArrayNode>(employee)

        arrayNode.forEach { node ->
            if (node is ObjectNode) {
                node.retain(firstLevel + secondLevel.map { it[0] })
                secondLevel.forEach { n ->
                    val nestedNode = node.get(n[0])
                    if (nestedNode is ObjectNode) {
                        nestedNode.retain(n[1])
                    }
                }
            }
        }
        return arrayNode
    }
}
