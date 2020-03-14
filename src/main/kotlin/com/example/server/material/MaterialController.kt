package com.example.server.material

import com.example.server.contract.Contract
import com.example.server.contract.ContractService
import com.example.server.main.MvcController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/material")
class MaterialController : MvcController<Material, MaterialService, MaterialsRepository>() {
    @Autowired
    private val contractService: ContractService? = null

    override fun getInstance(): Material = Material()

    override val viewTemplateName: String = "material"

    override fun labelOfInputSearch(): String =  "Поиск по названию"

    @GetMapping("/find-contract/{id}")
    fun findHolder(
            @PathVariable("id") id: String,
            @RequestParam(required = false, defaultValue = "") filter: String,
            model: Model
    ): String {
        model.addAttribute("contracts", contractService!!.find(filter))
        model.addAttribute("linkStart", "material/set-contract/$id")
        model.addAttribute("placeholder", "Поиск по номеру или описанию")
        model.addAttribute("filterValue", filter)
        return "search"
    }

    @GetMapping("set-contract/{materialId}/{contractId}")
    fun setHolder(
            @PathVariable("materialId") material: Material,
            @PathVariable("contractId") contract: Contract?
    ): String {
        material.contract = contract
        repository!!.save(material)
        return "redirect:/material/" + material.id
    }
}