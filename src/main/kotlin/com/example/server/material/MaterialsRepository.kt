package com.example.server.material

import com.example.server.contract.Contract
import org.springframework.data.jpa.repository.JpaRepository

interface MaterialsRepository : JpaRepository<Material, Int> {
    fun findByNameContainingOrderByName(name: String): List<Material>
    fun findByContract(contract: Contract): List<Material>
}