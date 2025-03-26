package com.tuapp.instagramautomation

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent
import android.util.Log

@RunWith(AndroidJUnit4::class)
class InstagramAutomationTest {

    @Test
    fun automateInstagram() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // Ir a inicio
        device.pressHome()
        Thread.sleep(2000)

        // Abrir Instagram
        val packageManager = context.packageManager
        val instagramIntent = packageManager.getLaunchIntentForPackage("com.instagram.android")

        if (instagramIntent != null) {
            instagramIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            Log.d("InstagramAutomation", "Ejecutando Intent para abrir Instagram")
            context.startActivity(instagramIntent)
        } else {
            Log.e("InstagramAutomation", "No se encontró Instagram instalado en el dispositivo")
            return
        }

        Thread.sleep(2000) // Esperar a que cargue Instagram

        // Seleccionar el botón de perfil (usualmente en la parte inferior derecha)
        val profileButton = device.findObject(UiSelector().resourceId("com.instagram.android:id/profile_tab"))

        if (profileButton.exists()) {
            Log.d("InstagramAutomation", "Seleccionando perfil")
            profileButton.click()
        } else {
            Log.e("InstagramAutomation", "No se encontró el botón de perfil")
        }

        Thread.sleep(2000) // Esperar a que cargue Instagram
        val firstPhoto = device.findObject(
            UiSelector().className("android.widget.Button").instance(4)
        )

        if (firstPhoto.exists() && firstPhoto.isEnabled()) {
            Log.d("InstagramAutomation", "Seleccionando la primera foto")
            firstPhoto.click()
        } else {
            Log.e("InstagramAutomation", "No se encontró la primera foto")
        }

        // Buscar el botón de comentarios por su resource-id
        val commentButton = device.findObject(
            UiSelector().resourceId("com.instagram.android:id/row_feed_button_comment")
        )

        Thread.sleep(2000) // Esperar a que cargue Instagram

        // Verificar si existe y hacer clic
        if (commentButton.exists() && commentButton.isEnabled) {
            Log.d("InstagramAutomation", "Seleccionando el botón de comentarios")
            commentButton.click()
        } else {
            Log.e("InstagramAutomation", "No se encontró el botón de comentarios")
        }

        Thread.sleep(2000) // Esperar a que cargue Instagram

        // Buscar la caja de comentarios
        val commentBox = device.findObject(
            UiSelector().resourceId("com.instagram.android:id/layout_comment_thread_edittext")
        )

        // Hacer clic en la caja de comentarios
        if (commentBox.exists() && commentBox.isEnabled) {
            Log.d("InstagramAutomation", "Seleccionando la caja de comentarios")
            commentBox.click()
            Thread.sleep(2000) // Esperar para que el teclado aparezca

            // Pegar texto desde el portapapeles (Método 1)
            device.pressKeyCode(android.view.KeyEvent.KEYCODE_V, android.view.KeyEvent.META_CTRL_ON)

            // Opción alternativa en caso de que no funcione el método anterior
            // device.executeShellCommand("input keyevent 279")

        } else {
            Log.e("InstagramAutomation", "No se encontró la caja de comentarios")
        }

        Thread.sleep(2000) // Esperar a que cargue Instagram

        val postButton = device.findObject(
            UiSelector().resourceId("com.instagram.android:id/layout_comment_thread_post_button_icon")
        )

        if (postButton.exists() && postButton.isEnabled) {
            Log.d("InstagramAutomation", "Publicando comentario")
            postButton.click()
            Thread.sleep(2000) // Esperamos un poco para que se publique correctamente
        } else {
            Log.e("InstagramAutomation", "No se encontró el botón de Publicar")
        }






    }
}
