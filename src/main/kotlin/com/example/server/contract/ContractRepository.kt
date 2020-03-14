package com.example.server.contract

import org.springframework.data.jpa.repository.JpaRepository

interface ContractRepository : JpaRepository<Contract, Int> {
    fun findByDescriptionContainingOrderByNum(name: String): List<Contract>
}