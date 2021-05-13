package com.devvailonge.flip.features.flashcard.delete.presentation.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.devvailonge.flip.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FlashCardDeleteDialog : DialogFragment() {

    private var _listener: (() -> Unit)? = null

    fun setListener(listener: () -> Unit) {
        _listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(getString(R.string.text_title_delete_dialog_flash))
            .setMessage(getString(R.string.text_message_dialog_flash))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                _listener?.let { yes ->
                    yes()
                }
            }
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
    }
}