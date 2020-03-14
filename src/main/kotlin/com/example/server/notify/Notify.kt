package com.example.server.notify

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
data class Notify (
    var text: String = "",
    var type: String = "",
    var actions: MutableList<Action> = ArrayList()
) {
    @AllArgsConstructor
    data class Action (
        var text: String = "",
        var actionHref: String = ""
    )
}