package org.fossify.messages.dialogs

import androidx.appcompat.app.AlertDialog
import org.fossify.commons.activities.BaseSimpleActivity
import org.fossify.commons.extensions.getAlertDialogBuilder
import org.fossify.commons.extensions.setupDialogStuff
import org.fossify.commons.extensions.showKeyboard
import org.fossify.commons.extensions.value
import org.fossify.messages.databinding.DialogEditSmsForwardingNumberBinding
import org.fossify.messages.extensions.config

class EditSmsForwardingNumberDialog(val activity: BaseSimpleActivity, val callback: () -> Unit) {
    init {
        val binding = DialogEditSmsForwardingNumberBinding.inflate(activity.layoutInflater).apply {
            editSmsForwardingNumberEdittext.setText(activity.config.forwardSmsNumber)
        }

        activity.getAlertDialogBuilder()
            .setPositiveButton(org.fossify.commons.R.string.ok, null)
            .setNegativeButton(org.fossify.commons.R.string.cancel, null)
            .apply {
                activity.setupDialogStuff(binding.root, this) { alertDialog ->
                    alertDialog.showKeyboard(binding.editSmsForwardingNumberEdittext)
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val newForwardNumber = binding.editSmsForwardingNumberEdittext.value
                        activity.config.forwardSmsNumber = newForwardNumber
                        callback()
                        alertDialog.dismiss()
                    }
                }
            }
    }
}

