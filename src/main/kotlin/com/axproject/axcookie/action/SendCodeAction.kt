package com.axproject.axcookie.action

import com.axproject.axcookie.dialog.CodeDialogWrapper
import com.axproject.axcookie.util.ValueUtils
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages

class SendCodeAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        val editor = event.getData(PlatformDataKeys.EDITOR)

        if (editor == null) {
            openErrorMessage(ValueUtils.ERROR_EDITOR)
        }

        val code = editor!!.selectionModel.selectedText

        if (code == null) {
            openErrorMessage(ValueUtils.ERROR_CODE)
        }

        CodeDialogWrapper(code!!).showAndGet()
    }

    private fun openErrorMessage(error: String) {
        Messages.showMessageDialog(error, ValueUtils.ERROR_TITLE, Messages.getErrorIcon())
    }

}