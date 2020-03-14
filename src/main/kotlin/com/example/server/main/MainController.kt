package com.example.server.main

import com.example.server.contract.ContractRepository
import com.example.server.employee.EmployeeRepository
import com.example.server.equipment.EquipmentRepository
import com.example.server.material.MaterialsRepository
import com.example.server.notify.NotifyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @Autowired
    lateinit var contractRepository: ContractRepository
    @Autowired
    lateinit var employeeRepository: EmployeeRepository
    @Autowired
    lateinit var equipmentRepository: EquipmentRepository
    @Autowired
    lateinit var materialsRepository: MaterialsRepository
    @Autowired
    lateinit var notifyService: NotifyService

    @GetMapping
    fun main(model: Model): String {
        model.addAttribute("notifies", notifyService.getNotifies())
        model.addAttribute("contracts", contractRepository.findAll())
        model.addAttribute("employees", employeeRepository.findAll())
        model.addAttribute("equipments", equipmentRepository.findAll())
        model.addAttribute("materials", materialsRepository.findAll())
        return "index"
    }
}