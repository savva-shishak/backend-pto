package com.example.server.contract

import com.example.server.employee.EmployeeService
import com.example.server.equipment.Equipment
import com.example.server.main.MvcService
import com.example.server.material.MaterialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.ui.Model

@Service
class ContractService : MvcService<Contract, ContractRepository>() {
    @Autowired
    private val employeeService: EmployeeService? = null
    @Autowired
    private val materialService: MaterialService? = null

    override fun setDataFromMap(map: Map<String, String>, contract: Contract) {
        val id = map["id"]
        if (id != null && id != "") {
            contract.id = Integer.valueOf(id)
        }
        contract.num = map["num"] ?: ""
        contract.description = map["description"] ?: ""
        contract.sum = java.lang.Long.valueOf(map["sum"])
        contract.dateStart = map["dateStart"] ?: ""
        contract.dateEnd = map["dateEnd"] ?: ""

        contract.equipments = equipmentRepository!!.findAllById(toIntegerList(map["equipments"] ?: ""))
        repository!!.save(contract)

        employeeService!!.findAllAndAddContract(contract, toIntegerList(map["employees"] ?: ""))
        materialService!!.findAllAndSetContract(contract, toIntegerList(map["materials"] ?: ""))
    }

    override fun find(searchKey: String): List<Contract> {
        return repository!!.findByDescriptionContainingOrderByNum(searchKey)
    }

    override fun settingModel(model: Model, contract: Contract) {
        model.addAttribute("employees", indexesToString(employeeService!!.findAllByContract(contract)))
        model.addAttribute("materials", indexesToString(materialRepository!!.findByContract(contract)))
        model.addAttribute("equipments", indexesToString(contract.equipments))
    }

    fun removeEquipment(equipment: Equipment) {
        val contracts = repository!!.findAll()
        for (c in contracts) {
            c.equipments.remove(equipment)
        }
        repository!!.saveAll(contracts)
    }
}
