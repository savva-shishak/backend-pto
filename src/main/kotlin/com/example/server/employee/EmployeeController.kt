package com.example.server.employee

import com.example.server.equipment.EquipmentService
import com.example.server.main.MvcController
import com.example.server.tabel.MonthRepository
import com.example.server.tabel.TabelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/employee")
class EmployeeController : MvcController<Employee, EmployeeService, EmployeeRepository>() {
    @Autowired
    private val equipmentService: EquipmentService? = null

    @Autowired
    lateinit var tabelService: TabelService

    override fun getInstance(): Employee{
        return Employee()
    }

    override val viewTemplateName: String
        get() = "employee"

    override fun labelOfInputSearch(): String {
        return "Поиск по ФИО"
    }

    @GetMapping("/delete/{id}")
    override fun delete(@PathVariable("id") model: Employee): String {
        equipmentService!!.removeHolder(model)
        tabelService.findByEmployeeAndToDelete(model)
        return super.delete(model)
    }
}