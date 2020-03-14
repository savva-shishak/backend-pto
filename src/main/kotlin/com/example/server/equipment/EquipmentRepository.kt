package com.example.server.equipment

import com.example.server.employee.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EquipmentRepository : JpaRepository<Equipment, Int> {
    fun findByNameContainingOrderByName(name: String): List<Equipment>
    fun findByHolder(holder: Employee?): List<Equipment>
}