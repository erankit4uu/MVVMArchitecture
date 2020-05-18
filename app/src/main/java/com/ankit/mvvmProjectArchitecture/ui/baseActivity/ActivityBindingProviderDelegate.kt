package com.ankit.mvvmProjectArchitecture.ui.baseActivity

import android.app.Activity
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ankit.mvvmProjectArchitecture.databinding.CustomActionbaractivityWithBackBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityBindingProviderDelegate<out T : ViewDataBinding>(
    private val baseAppCompatActivity: BaseActivity, @LayoutRes private val layoutRes: Int) :
    ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createBinding(
            baseAppCompatActivity.getParentBinding()).also { binding = it }
    }

    private fun createBinding(bindingParent: CustomActionbaractivityWithBackBinding): T {

        val inflator = LayoutInflater.from(bindingParent.llInflatorContainer?.context)
        return DataBindingUtil.inflate(inflator, layoutRes, bindingParent.llInflatorContainer,
            true)
    }
}