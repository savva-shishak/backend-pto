package com.example.server.employee

import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Int> {
    fun findByNameContainingOrderByName(name: String): List<Employee>
}