package com.example.server.material

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
class Material : DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Just::class)
    override var id: Int = 0

    @JsonView(Just::class)
    var name: String = ""

    @JsonView(Just::class)
    var num: Long = 0

    @JsonView(Just::class)
    var units: String = ""

    @ManyToOne
    @JsonView(Full::class)
    var contract: Contract? = null

}