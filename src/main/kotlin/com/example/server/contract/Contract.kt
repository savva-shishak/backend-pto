package com.example.server.contract

import com.example.server.equipment.Equipment
import com.example.server.main.DataModel
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
    override var id: Int = 0;
    var num: String = ""
    var sum: Long = 0
    var description: String = ""
    var dateStart: String = ""
    var dateEnd: String = ""
    @OneToMany
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