package com.example.server.equipment

import com.example.server.employee.Employee
import com.example.server.main.DataModel
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
    override var id: Int = 0
    var name: String = ""
    var model: String = ""
    var modelNumber: String = ""
    var inventoryNumber: String = ""
    var serialNumber: String = ""
    var state: String = ""
    @ManyToOne
    var holder: Employee? = null
}