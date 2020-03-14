package com.example.server.employee

import com.example.server.contract.Contract
import com.example.server.main.DataModel
import com.example.server.tabel.Month
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Employee : DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Int = 0
    var name: String = ""
    var phone: Long = 0
    var tabelNumber: String = ""
    var placeOfRegistration: String = ""
    var placeOfResidence: String = ""
    var position: String = ""

    @ManyToMany
    var contracts: MutableList<Contract> = ArrayList()
}