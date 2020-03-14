package com.example.server.tabel

import com.example.server.employee.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface MonthRepository : JpaRepository<Month, Long> {
    fun findByEmployee(employee: Employee): MutableList<Month>
}