package com.example.server.material

import com.example.server.contract.Contract
import com.example.server.main.MvcService
import org.springframework.stereotype.Service
import org.springframework.ui.Model

@Service
class MaterialService : MvcService<Material, MaterialsRepository>() {
    override fun find(searchKey: String): List<Material> {
        return repository!!.findByNameContainingOrderByName(searchKey)
    }

    override fun setDataFromMap(map: Map<String, String>, material: Material) {
        val id = map["id"]
        if (id != null && id != "") {
            material.id = Integer.valueOf(id)
        }
        material.num = java.lang.Long.valueOf(map["num"])
        material.name = map["name"]?: ""
        material.units = map["units"]?: ""
    }

    override fun settingModel(model: Model, material: Material) {
        model.addAttribute("contract", material.contract)
    }

    fun findAllAndSetContract(contract: Contract?, indexes: List<Int?>?) {
        for (m in repository!!.findAllById(indexes!!)) {
            m.contract = contract
            repository!!.save(m!!)
        }
    }

    fun removeContract(contract: Contract) {
        val materials = repository!!.findAll()
        for (material in materials) {
            material.contract = null
        }
    }
}