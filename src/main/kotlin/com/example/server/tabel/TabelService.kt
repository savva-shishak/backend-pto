package com.example.server.tabel

import com.example.server.employee.Employee
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TabelService {
    @Autowired
    lateinit var repository: MonthRepository

    fun findByEmployeeAndToDelete(employee: Employee) {
        repository.deleteAll(repository.findByEmployee(employee))
    }

    fun createSheet(months: List<DataForExcel>) {
        val book = XSSFWorkbook()

        val sheet = book.cloneSheet(1)

        var rowNumber = 0

        for (month in months) {
            val row = sheet.createRow(rowNumber)

            createName(row, month)

            rowNumber += 2
        }
    }

    fun createName(row: XSSFRow, month: DataForExcel) {
        row.createCell(0)
    }

    data class DataForExcel(val employeeName: String, val days: Array<String>)
}