package com.example.server.employee

import com.example.server.contract.Contract
import com.example.server.equipment.EquipmentService
import com.example.server.main.MvcService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.*

@Service
class EmployeeService : MvcService<Employee, EmployeeRepository>() {
    @Autowired
    private val equipmentService: EquipmentService? = null

    override fun setDataFromMap(map: Map<String, String>, employee: Employee) {
        val id = map["id"]
        if (id != null && id != "") {
            employee.id = Integer.valueOf(id)
        }
        employee.name = map["name"]?: ""
        employee.phone = java.lang.Long.valueOf(map["phone"])
        employee.position = map["position"]?: ""
        employee.tabelNumber = map["tabelNumber"]?: ""
        employee.placeOfRegistration = map["placeOfRegistration"]?: ""
        if (map["placeOfResidence"] == "") {
            employee.placeOfResidence = map["placeOfRegistration"]?: ""
        } else {
            employee.placeOfResidence = map["placeOfResidence"]?: ""
        }
        employee.contracts = contractRepository!!.findAllById(toIntegerList(map["contracts"]?: ""))

        repository!!.save(employee)
        val equipmentsIndexesList = toIntegerList(map["equipments"]?: "0")
        equipmentService!!.findAllEndSetHolder(employee, equipmentsIndexesList)
    }

    override fun find(searchKey: String): List<Employee> {
        return employeeRepository!!.findByNameContainingOrderByName(searchKey)
    }

    override fun settingModel(model: Model, employee: Employee) {
        val equipments = equipmentRepository!!.findByHolder(employee)
        model.addAttribute("equipments", indexesToString(equipments))
        model.addAttribute("contracts", indexesToString(employee.contracts))
    }

    fun findAllAndAddContract(contract: Contract, indexes: List<Int?>?) {
        for (e in repository!!.findAllById(indexes!!)) {
            val contractsOfEmployee = e.contracts
            if (!contractsOfEmployee.contains(contract)) {
                contractsOfEmployee.add(contract)
            }
            repository!!.save(e!!)
        }
    }

    fun findAllByContract(contract: Contract): List<Employee> {
        val employees = repository!!.findAll()
        val resultList: MutableList<Employee> = ArrayList()

        for (e in employees) {
            for (c in e.contracts) {
                if (c.id === contract.id) {
                    resultList.add(e)
                    break
                }
            }
        }
        return resultList
    }

    fun removeContract(contract: Contract?) {
        val employees = repository!!.findAll()
        for (e in employees) {
            e.contracts.remove(contract)
        }
        repository!!.saveAll(employees)
    }
}