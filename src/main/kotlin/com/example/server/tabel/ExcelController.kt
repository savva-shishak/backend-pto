package com.example.server.tabel

import com.example.server.employee.EmployeeRepository
import com.example.server.employee.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/excel")
class ExcelController {

    @Autowired
    lateinit var monthRepository: MonthRepository

    @Autowired
    lateinit var employeeService: EmployeeService

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @GetMapping("/select")
    fun selectTabels(): String {
        return "select-tabels"
    }

    @GetMapping("/get")
    fun getExcel(@RequestParam indexes: String) {

    }
}