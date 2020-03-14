package com.example.server.contract

import com.example.server.equipment.Equipment
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
class Contract : DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Just::class)
    override var id: Int = 0

    @JsonView(Just::class)
    var num: String = ""

    @JsonView(Just::class)
    var sum: Long = 0

    @JsonView(Just::class)
    var description: String = ""

    @JsonView(Just::class)
    var dateStart: String = ""

    @JsonView(Just::class)
    var dateEnd: String = ""

    @OneToMany
    @JsonView(Full::class)
    var equipments: MutableList<Equipment> = ArrayList()

    fun getDescription(maxLength: Int): String {
        if (description!!.length <= maxLength) {
            return description
        }
        var maxDescriptionString = ""
        for (i in 0 until maxLength) {
            maxDescriptionString += description!![i]
        }
        return "$maxDescriptionString..."
    }
}