package com.ak47.cms.cms.result

class Result<T> {
    var data: T? = null
    var message: String? = null
    var sucesss: Boolean = false

    constructor(data: T?, message: String?, sucesss: Boolean) {
        this.data = data
        this.message = message
        this.sucesss = sucesss
    }
}
