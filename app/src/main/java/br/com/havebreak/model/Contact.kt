package br.com.havebreak.model

import java.io.Serializable

data class Contact(var id:Int, var name:String, var username:String, var password:String): Serializable
