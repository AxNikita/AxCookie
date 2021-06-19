package com.axproject.axcookie.dialog

import com.axproject.axcookie.util.MailUtils
import com.axproject.axcookie.util.ValueUtils
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import java.awt.*
import java.awt.datatransfer.StringSelection
import java.util.*
import java.util.Timer
import javax.imageio.ImageIO
import javax.swing.*


class CodeDialogWrapper(private val code: String) : DialogWrapper(true) {

    init {
        init()
        title = ValueUtils.DIALOG_TITLE
    }

    override fun createCenterPanel(): JComponent {

        val dialogPanel = JPanel(BorderLayout())

        dialogPanel.add(createCodeArea(), BorderLayout.CENTER)
        dialogPanel.add(createButtonsPanel(), BorderLayout.PAGE_END)

        return dialogPanel
    }

    private fun createCodeArea(): JComponent {

        val codeComponent = JTextArea(code)
        codeComponent.preferredSize = Dimension(400, 350)

        copyCodeToClipboard(code)

        return codeComponent
    }

    private fun createButtonsPanel(): JPanel {

        val buttonsPanel = JPanel(FlowLayout())

        val codeLabel = createCodeLabel()

        buttonsPanel.add(codeLabel)
        buttonsPanel.add(createSendToMailButton())
        buttonsPanel.add(createCookieButton())

        checkClipboardTimer(codeLabel)

        return buttonsPanel
    }

    private fun createSendToMailButton(): JButton {

        val button = createButton(ValueUtils.BUTTON_MAIL_TITLE, ValueUtils.BUTTON_MAIL_IMAGE, Dimension(110, 40))

        button.addActionListener {
            MailUtils.sendMail(code)
            Messages.showMessageDialog(
                ValueUtils.MESSAGES_MAIL_MESSAGE,
                ValueUtils.MESSAGES_MAIL_TITLE,
                Messages.getInformationIcon()
            )
        }

        return button
    }

    private fun createCookieButton(): JButton {

        val button = createButton(ValueUtils.BUTTON_COOKIE_TITLE, ValueUtils.BUTTON_COOKIE_IMAGE, Dimension(110, 40))

        button.addActionListener {
            Messages.showMessageDialog(
                ValueUtils.MESSAGES_COOKIE_MESSAGE,
                ValueUtils.MESSAGES_COOKIE_TITLE,
                Messages.getInformationIcon()
            )
        }

        return button
    }

    private fun createCodeLabel(): JLabel {

        val label = JLabel(ValueUtils.LABEL_CHECK_TITLE)
        label.foreground = Color.GREEN

        return label
    }

    private fun createButton(title: String, imageUrl: String, size: Dimension): JButton {
        //button.horizontalTextPosition = SwingConstants.LEFT
        val button = JButton(title)
        button.icon = ImageIcon(ImageIO.read(javaClass.classLoader.getResource(imageUrl)))
        button.preferredSize = size
        return button
    }

    private fun copyCodeToClipboard(code: String) {
        val codeSelection = StringSelection(code)
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(codeSelection, null)
    }

    private fun checkClipboardTimer(label: JLabel) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                label.isVisible = false
                label.isEnabled = false
            }
        }, 1500)
    }

}