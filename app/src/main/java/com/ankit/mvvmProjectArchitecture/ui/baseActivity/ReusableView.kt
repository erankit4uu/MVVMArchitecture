package com.ankit.mvvmProjectArchitecture.ui.baseActivity

interface ReusableView {
    fun showToast(msg: String)
    fun showProgress()
    fun hideProgress()
}