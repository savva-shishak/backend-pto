package com.example.server.tabel

import com.example.server.employee.Employee
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var name: String = ""

    val days: Array<String> = Array(31, fun(_) = "Х")

    @OneToOne
    var employee: Employee = Employee()
}