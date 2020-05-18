package com.ankit.mvvmProjectArchitecture.ui.baseActivity

import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.ankit.mvvmProjectArchitecture.ArchitectureApp
import com.ankit.mvvmProjectArchitecture.R
import com.ankit.mvvmProjectArchitecture.databinding.CustomActionbaractivityWithBackBinding
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import java.security.AccessController.getContext

abstract class BaseActivity : AppCompatActivity(), ReusableView {

    abstract fun init()

    private var isBackPressDialogToShow = false
    private var view: View? = null
    private lateinit var bindingParent: CustomActionbaractivityWithBackBinding
    lateinit var snackBar: Snackbar


    val fragmentActivity: FragmentActivity = this

    /**
     * View Binding setContentView(id)
     */
    fun setContentBindingTemp() {
        bindingParent = DataBindingUtil.setContentView(
            this,
            R.layout.custom_actionbaractivity_with_back
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ArchitectureApp.instance.component.inject(this)

        setContentBindingTemp()
        initializeViewBindingTemp()

    }

    private fun initializeViewBindingTemp() {
        view = bindingParent?.root

        initializeOtherViews()

        setToolbar()
        showToolbar()
        showBack()
        applyDefaultFont()
//        isOnline()
        init()
    }

    private fun initializeOtherViews() {
        bindingParent.includeToolbar!!.llToolbarBack.setOnClickListener { _ -> onBackPressed() }
    }

    fun getParentBinding(): CustomActionbaractivityWithBackBinding {
        return bindingParent
    }

    fun getView(): View? {
        return view
    }

    fun applyDefaultFont() {
        //ArchitectureApp.instance.settingFontToViewGroup(UBUNTU_REGULAR, view)
    }

    fun setToolbar() {
        setSupportActionBar(bindingParent.toolbarMain)
    }

    fun showBack() {
        showToolbar()
        bindingParent.includeToolbar!!.ivToolbarBack.visibility = View.VISIBLE
    }

    fun hideBack() {
        bindingParent.includeToolbar!!.ivToolbarBack.visibility = View.GONE
    }

    fun showToolbar() {
        supportActionBar?.show()
    }

    fun hideToolbar() {
        supportActionBar?.hide()
    }

    fun setTitleCustom(title: String?) {
        bindingParent.includeToolbar?.tvToolbarTitle?.text = title ?: ""
    }

    fun setIsBackPressDialogToShow(isBackPressDialogToShow: Boolean) {
        this.isBackPressDialogToShow = isBackPressDialogToShow
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

//    override fun showToast(msg: String) {
//        msg.exShowToast(getContext())
//    }




//    fun isOnline(): Boolean {
//        EventBus.getDefault().post(NetworkUtil.isOnline(getContext()))
//        return NetworkUtil.isOnline(getContext());
//    }


//    override fun onBackPressed() {
//        when (isBackPressDialogToShow) {
//            true -> {
//                var msg = "You really want to exit?"
//                var fragment = CommonDialogFragment.newInstance(msg)
//                fragment.show(supportFragmentManager, "")
//                fragment.setListener({ fragment.dismiss() }, { super.onBackPressed() })
//            }
//            false -> {
//                super.onBackPressed()
//            }
//        }
//
//    }


    override fun showProgress() {
        bindingParent.progressBar.visibility = View.VISIBLE
        bindingParent.tvProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        bindingParent.progressBar.visibility = View.GONE
        bindingParent.tvProgress.visibility = View.GONE

    }
}