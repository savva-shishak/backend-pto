package com.example.server.tabel

import com.example.server.employee.Employee
import com.example.server.employee.EmployeeRepository
import com.example.server.employee.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/tabel")
class TabelController {

    @Autowired
    lateinit var repository: MonthRepository

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("{id}")
    fun get(@PathVariable("id") employee: Employee, model: Model): String {

        model.addAttribute("tabel", repository.findByEmployee(employee))
        model.addAttribute("employee", employee)

        return "tabel"
    }

    @GetMapping("/create/{employeeId}")
    fun create(@PathVariable("employeeId") employee: Employee, @RequestParam name: String, model: Model): String {
        val newMonth = Month()

        newMonth.name     = name
        newMonth.employee = employee

        repository.save(newMonth)

        return "redirect:/tabel/${employee.id}"
    }

    @GetMapping("/save/{id}")
    fun save(@PathVariable("id") month: Month, @RequestParam days: Map<String, String>): String {

        for (day in days) {
            val indexDay = day.key.toInt()
            val numberWorkHrs = day.value
            month.days[indexDay] = numberWorkHrs;
        }

        repository.save(month)

        return "redirect:/tabel/${month.employee.id}"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable("id") month: Month): String {

        repository.delete(month)

        return "redirect:/tabel/${month.employee.id}"
    }
}