package com.example.server.equipment

import com.example.server.contract.ContractService
import com.example.server.employee.Employee
import com.example.server.employee.EmployeeService
import com.example.server.main.MvcController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/equipment")
class EquipmentController : MvcController<Equipment, EquipmentService, EquipmentRepository>() {
    @Autowired
    private val contractService: ContractService? = null
    @Autowired
    private val employeeService: EmployeeService? = null

    override fun getInstance() = Equipment()

    override val viewTemplateName = "equipment"

    override fun labelOfInputSearch() = "Поиск по названию"

    @GetMapping("/delete/{id}")
    override fun delete(@PathVariable("id") equipment: Equipment): String {
        contractService!!.removeEquipment(equipment)
        return super.delete(equipment)
    }

    @GetMapping("/find-holder/{id}")
    fun findHolder(
            @PathVariable("id") id: String,
            @RequestParam(required = false, defaultValue = "") filter: String,
            model: Model
    ): String {
        model.addAttribute("employees", employeeService!!.find(filter))
        model.addAttribute("linkStart", "equipment/setHolder/$id")
        model.addAttribute("placeholder", "Поиск по ФИО")
        model.addAttribute("filterValue", filter)
        return "search"
    }

    @GetMapping("setHolder/{equipmentId}/{holderId}")
    fun setHolder(
            @PathVariable("equipmentId") equipment: Equipment,
            @PathVariable("holderId") employee: Employee?
    ): String {
        equipment.holder = employee
        repository!!.save(equipment)
        return "redirect:/equipment/" + equipment.id
    }
}