package com.gauravbora.readaddupdateanddeletedatafromrealtimedb

data class UserData(val name:String?="",val email:String?="") {

    fun getMap(): Map<String, String?> {
        return mapOf(
            "name" to name
        )
    }
}
