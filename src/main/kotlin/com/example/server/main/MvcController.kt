package com.example.server.main

import com.example.server.notify.NotifyService
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

abstract class MvcController<M : DataModel, S : MvcService<M, R>, R : JpaRepository<M, Int>> {
    @Autowired
    protected var service: S? = null
    @Autowired
    protected var repository: R? = null
    @Autowired
    protected var notifyService: NotifyService? = null

    abstract fun getInstance(): M
    abstract val viewTemplateName: String
    abstract fun labelOfInputSearch(): String
    @GetMapping
    fun main(
            @RequestParam(required = false, defaultValue = "") filter: String,
            model: Model
    ): String {
        model.addAttribute(viewTemplateName + "s", service!!.find(filter))
        model.addAttribute("addingLink", "/$viewTemplateName/add")
        model.addAttribute("linkStart", viewTemplateName)
        model.addAttribute("filterValue", filter)
        model.addAttribute("placeholder", labelOfInputSearch())
        model.addAttribute("notifies", notifyService!!.getNotifies())
        return "search"
    }

    @GetMapping("{id}")
    operator fun get(
            @PathVariable("id") obj: M,
            model: Model
    ): String {
        model.addAttribute(viewTemplateName, obj)
        model.addAttribute("notifies", notifyService!!.getNotifies())
        service!!.settingModel(model, obj)
        return viewTemplateName
    }

    @GetMapping("/add")
    fun add(model: Model): String {
        model.addAttribute(viewTemplateName, getInstance())
        model.addAttribute("notifies", notifyService!!.getNotifies())
        return viewTemplateName
    }

    @PostMapping
    fun save(@RequestParam data: Map<String, String>): String {
        val model = if (data["id"] != "0") {
            repository!!.findById(Integer.valueOf(data["id"])).get()
        } else {
            getInstance()
        }
        service!!.setDataFromMap(data, model)
        repository!!.save(model)
        return "redirect:$viewTemplateName"
    }

    @GetMapping("/delete/{id}")
    open fun delete(@PathVariable("id") m: M): String {
        repository!!.delete(m)
        return "redirect:/$viewTemplateName"
    }

    @GetMapping("/api")
    @JsonView(DataModel.Just::class)
    @ResponseBody
    fun api(@RequestParam filter: String): List<M> {
        return service!!.find(filter)
    }

    @GetMapping("/findAll")
    @JsonView(DataModel.Just::class)
    @ResponseBody
    fun apiIndexes(@RequestParam indexes: String): List<M> {
        return repository!!.findAllById(service!!.toIntegerList(indexes))
    }
}