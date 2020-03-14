package com.example.server.contract

import com.example.server.employee.EmployeeService
import com.example.server.main.MvcController
import com.example.server.material.MaterialService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/contract")
class ContractController : MvcController<Contract, ContractService, ContractRepository>() {
    @Autowired
    private val materialService: MaterialService? = null
    @Autowired
    private val employeeService: EmployeeService? = null

    override fun getInstance(): Contract {
        return Contract()
    }

    override val viewTemplateName: String = "contract"

    override fun labelOfInputSearch(): String {
        return "Поиск по описанию"
    }

    @GetMapping("/delete/{id}")
    override fun delete(@PathVariable("id") m: Contract): String {
        materialService!!.removeContract(m)
        employeeService!!.removeContract(m)
        return super.delete(m)
    }
}