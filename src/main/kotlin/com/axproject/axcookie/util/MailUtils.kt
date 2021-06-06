package com.axproject.axcookie.util

import java.awt.Desktop
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


class MailUtils {

    companion object {

        fun sendMail(code: String) {
            val subject = encodeText("This is my perfect code!")
            val body = encodeText(code)
            Desktop.getDesktop().browse(URI("mailto:?subject=$subject&body=$body"))
        }

        private fun encodeText(text: String): String {
            return URLEncoder.encode(text, StandardCharsets.UTF_8.toString()).replace("+", "%20")
        }

    }

}