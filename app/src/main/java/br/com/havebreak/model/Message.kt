package br.com.havebreak.model

data class Message (
    var text:String,
    var sender:Contact,
    var recipient:Contact
)