package com.ankit.mvvmProjectArchitecture.implementation

interface EndPoint {

    /** The base API URL. */
    val url: String?

    /** A name for differentiating multiple API URLs */
    val name: String?

    fun setEndPoint(url: String): ApiEndPointImpl
}