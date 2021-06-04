package com.axproject.axcookie.action

import com.axproject.axcookie.dialog.SampleDialogWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages

class SendCodeAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        val editor = event.getData(PlatformDataKeys.EDITOR)

        if (editor == null) {
            Messages.showMessageDialog("Ахтунг 1", "Code", Messages.getErrorIcon())
        }

        val code = editor!!.selectionModel.selectedText

        if (code == null) {
            Messages.showMessageDialog("Ахтунг 2", "Code", Messages.getErrorIcon())
        }

//        var code: String? = ""
//
//        try {
//            code = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString())
//        } catch (e: UnsupportedEncodingException) {
//            Messages.showMessageDialog("Ахтунг 3", "Code", Messages.getErrorIcon())
//        }

//        Messages.showMessageDialog(code, "Code", Messages.getInformationIcon())

        SampleDialogWrapper(code!!).showAndGet()
    }

}