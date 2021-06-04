package com.axproject.axcookie.dialog

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent
import javax.swing.JPanel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JLabel

class SampleDialogWrapper(code: String) : DialogWrapper(true) {

    init {
        init()
        title = "Test DialogWrapper"
    }

    override fun createCenterPanel(): JComponent {
        val dialogPanel = JPanel(BorderLayout())
        val label = JLabel("testing")
        label.preferredSize = Dimension(100, 100)
        dialogPanel.add(label, BorderLayout.CENTER)
        return dialogPanel
    }

}