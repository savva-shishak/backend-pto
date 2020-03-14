package com.example.server.main

import com.example.server.contract.ContractRepository
import com.example.server.employee.EmployeeRepository
import com.example.server.equipment.EquipmentRepository
import com.example.server.material.MaterialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.*

@Service
abstract class MvcService<M : DataModel?, R : JpaRepository<*, *>?> {
    @Autowired
    protected var repository: R? = null
    @Autowired
    protected var contractRepository: ContractRepository? = null
    @Autowired
    protected var employeeRepository: EmployeeRepository? = null
    @Autowired
    protected var equipmentRepository: EquipmentRepository? = null
    @Autowired
    protected var materialRepository: MaterialsRepository? = null

    abstract fun find(searchKey: String): List<M>
    abstract fun setDataFromMap(map: Map<String, String>, m: M)
    abstract fun settingModel(model: Model, m: M)

    fun toIntegerList(string: String): List<Int> {
        val integers: MutableList<Int> = ArrayList()
        if (string.isEmpty()) {
            return integers
        }
        for (str in string.split(" ").toTypedArray()) {
            integers.add(str.toInt())
        }
        return integers
    }

    fun indexesToString(models: List<DataModel>): String {
        val indexes: MutableList<String> = ArrayList()
        for (m in models) {
            indexes.add(m.id.toString() + "")
        }
        return java.lang.String.join(" ", indexes)
    }

    fun test(): String {
        return "Hello world"
    }
}