package com.aesc.restaurantews.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.extensions.loadByURL
import com.aesc.restaurantews.extensions.toast
import com.aesc.visaappk.provider.services.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_dialog_especialidades.*


class BottomSheetDialogResult : BottomSheetDialogFragment(), View.OnClickListener {
    private val bottomSheetDialog: BottomSheetDialog by lazy {
        BottomSheetDialog(
            requireContext(),
            theme
        )
    }
    private var behavior: Int? = null
    lateinit var viewModels: MainViewModel

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun getTheme(): Int = R.style.BottomSheetMenuTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog_especialidades, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog.setOnShowListener {
            bottomSheetDialog.behavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetDialog.dismiss()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
        }
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        behavior = bottomSheetDialog.behavior.state
//        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        viewModels = ViewModelProvider(this).get(MainViewModel::class.java)
        btnContinuar.setOnClickListener(this)
        especialidad()
    }

    private fun especialidad() {
        var status = false
        viewModels.responseEspecialidadDia.observe(this, {
            if (!status){
                val especialidad = it.datos!!
                Utils.logsUtils("SUCCESS ${especialidad.url_foto}")
                img_especialidad.loadByURL(especialidad.url_foto!!)
                tv_nombre.text = especialidad.nombre
                tv_precio.text = especialidad.precio.toString()
                tv_descripcion.text = especialidad.descripcion
            }

        })

        viewModels.errorMessage.observe(this, {
            if (!status){
                Utils.logsUtils("ERROR $it")
                requireActivity().toast(it, Toast.LENGTH_LONG)
            }
        })

        viewModels.loading.observe(this, {
            status = it
            if (it) {
                Utils.logsUtils("SHOW")
            } else {
                Utils.logsUtils("HIDE")
            }
        })

        viewModels.especialidadDelDia()
    }

    override fun onClick(v: View?) {
        val ids = v!!.id
//        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        when (ids) {
            R.id.btnContinuar -> {
                dismiss()
            }
        }
    }
}