package com.example.server.employee

import com.example.server.contract.Contract
import com.example.server.main.DataModel
import com.example.server.main.DataModel.*
import com.fasterxml.jackson.annotation.JsonView
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
    @JsonView(Just::class)
    override var id: Int = 0

    @JsonView(Just::class)
    var name: String = ""

    @JsonView(Just::class)
    var phone: Long = 0

    @JsonView(Just::class)
    var tabelNumber: String = ""

    @JsonView(Just::class)
    var placeOfRegistration: String = ""

    @JsonView(Just::class)
    var placeOfResidence: String = ""

    @JsonView(Just::class)
    var position: String = ""

    @ManyToMany
    @JsonView(Full::class)
    var contracts: MutableList<Contract> = ArrayList()
}