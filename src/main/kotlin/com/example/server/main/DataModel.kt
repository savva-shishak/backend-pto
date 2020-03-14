package com.example.server.main

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

interface DataModel {
    var id: Int;

    interface Just

    interface Full: Just
}