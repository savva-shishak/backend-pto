package com.example.server.equipment

import com.example.server.main.DataModel.*
import com.example.server.employee.Employee
import com.example.server.main.DataModel
import com.fasterxml.jackson.annotation.JsonView
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Equipment : DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Just::class)
    override var id: Int = 0

    @JsonView(Just::class)
    var name: String = ""

    @JsonView(Just::class)
    var model: String = ""

    @JsonView(Just::class)
    var modelNumber: String = ""

    @JsonView(Just::class)
    var inventoryNumber: String = ""

    @JsonView(Just::class)
    var serialNumber: String = ""

    @JsonView(Just::class)
    var state: String = ""
    @ManyToOne

    @JsonView(Full::class)
    var holder: Employee? = null
}