package com.example.server.material

import com.example.server.contract.Contract
import com.example.server.main.DataModel
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
    override var id: Int = 0
    var name: String = ""
    var num: Long = 0
    var units: String = ""
    @ManyToOne
    var contract: Contract? = null
}