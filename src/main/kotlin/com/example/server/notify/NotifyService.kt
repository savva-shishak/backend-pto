package com.example.server.notify

import com.example.server.equipment.Equipment
import com.example.server.equipment.EquipmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class NotifyService {
    @Autowired
    private val equipmentRepository: EquipmentRepository? = null

    fun getNotifies(): List<Notify> {
        val notifies: MutableList<Notify> = ArrayList()
        notifies.addAll(checkEquipments())
        return notifies
    }

    private fun checkEquipments(): List<Notify> {
        val notifies: MutableList<Notify> = ArrayList()
        val equipmentsWithoutHolders = equipmentRepository!!.findByHolder(null)

        for (e in equipmentsWithoutHolders!!) {
            val actions = ArrayList<Notify.Action>()

            actions.add(Notify.Action("Назначить", "/equipment/find-holder/" + e!!.id))

            notifies.add(Notify(
                    "${e.name} ${e.model} ${e.modelNumber} остаётся без ответственного",
                    "error",
                    actions
            ))
        }

        return notifies
    }
}