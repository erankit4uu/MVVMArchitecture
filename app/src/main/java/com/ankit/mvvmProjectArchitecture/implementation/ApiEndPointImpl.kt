package com.ankit.mvvmProjectArchitecture.implementation

class ApiEndPointImpl : EndPoint {

    var sUrl: String? = null

    override val url: String?
    get() = sUrl

    override val name: String?
    get() = "Project" //To change initializer of created properties use File | Settings | File Templates.

    override fun setEndPoint(url: String): ApiEndPointImpl {
        this.sUrl = url
        return this
    }
}