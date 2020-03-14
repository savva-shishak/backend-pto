package com.example.server.equipment

import com.example.server.employee.Employee
import com.example.server.main.MvcService
import org.springframework.stereotype.Service
import org.springframework.ui.Model

@Service
class EquipmentService : MvcService<Equipment, EquipmentRepository>() {
    override fun find(searchKey: String): List<Equipment> {
        return repository!!.findByNameContainingOrderByName(searchKey)
    }

    override fun setDataFromMap(map: Map<String, String>, equipment: Equipment) {
        equipment.name = map["name"].toString()
        equipment.inventoryNumber = map["inventoryNumber"].toString()
        equipment.model = map["model"].toString()
        equipment.modelNumber = map["modelNumber"].toString()
        equipment.serialNumber = map["serialNumber"].toString()
        equipment.state = map["state"].toString()
    }

    fun findAllEndSetHolder(holder: Employee, indexes: List<Int>) {
        removeHolder(holder)
        for (e in repository!!.findAllById(indexes)) {
            e.holder = holder
            repository!!.save(e!!)
        }
    }

    override fun settingModel(model: Model, equipment: Equipment) {
        model.addAttribute("holder", equipment.holder)
    }

    fun removeHolder(holder: Employee) {
        val equipmentList = repository!!.findByHolder(holder)
        for (e in equipmentList) {
            e.holder = null
        }
        repository!!.saveAll(equipmentList)
    }
}