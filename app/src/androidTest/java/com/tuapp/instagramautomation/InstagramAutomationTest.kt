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
        Thread.sleep(1000)

        // Abrir TikTok
        val packageManager = context.packageManager
        val tiktokIntent = packageManager.getLaunchIntentForPackage("com.zhiliaoapp.musically")

        if (tiktokIntent != null) {
            tiktokIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            Log.d("TikTokAutomation", "Ejecutando Intent para abrir TikTok")
            context.startActivity(tiktokIntent)
        } else {
            Log.e("TikTokAutomation", "No se encontró TikTok instalado en el dispositivo")
            return
        }

        Thread.sleep(2000) // Esperar a que TikTok cargue la pantalla principal

        // Tocar el botón de búsqueda (coordenadas aproximadas)
        val x = 1000 // Coordenada X
        val y = 150  // Coordenada Y
        Log.d("TikTokAutomation", "Tocando el botón de búsqueda en las coordenadas ($x, $y)")
        device.click(x, y)

        Thread.sleep(1000) // Esperar tras tocar el botón de búsqueda

        // Localizar la barra de búsqueda por su clase o recurso
        val searchBox = device.findObject(
            UiSelector().className("android.widget.EditText")
        )

        if (searchBox.exists() && searchBox.isEnabled) {
            Log.d("TikTokAutomation", "Barra de búsqueda encontrada, escribiendo texto")
            searchBox.click()
            Thread.sleep(1000) // Esperar para que el teclado aparezca

            // Escribir el texto en la barra de búsqueda usando setText()
            searchBox.setText("@david_digitaladspaces")

            // Simular la tecla "Enter" para buscar
            Log.d("TikTokAutomation", "Presionando Enter para buscar")
            device.pressKeyCode(android.view.KeyEvent.KEYCODE_ENTER)
        } else {
            Log.e("TikTokAutomation", "No se encontró la barra de búsqueda")
            return
        }

        Thread.sleep(1000) // Esperar a que los resultados de búsqueda carguen

        // Tocar la foto de perfil para abrir el perfil
        val profilePhoto = device.findObject(
            UiSelector().resourceId("com.zhiliaoapp.musically:id/hz4")
        )

        if (profilePhoto.exists() && profilePhoto.isEnabled) {
            Log.d("TikTokAutomation", "Foto de perfil encontrada, seleccionando")
            profilePhoto.click()
        } else {
            Log.e("TikTokAutomation", "No se encontró la foto de perfil")
            return
        }

        Thread.sleep(1000) // Esperar a que el perfil del usuario cargue

        // Tocar el primer video usando instance(0)
        val firstVideo = device.findObject(
            UiSelector().resourceId("com.zhiliaoapp.musically:id/d2r").instance(1) // Aseguramos que seleccionamos el primer nodo
        )

        if (firstVideo.exists() && firstVideo.isEnabled) {
            Log.d("TikTokAutomation", "Primer video encontrado, seleccionando")
            firstVideo.click()
        } else {
            Log.e("TikTokAutomation", "No se encontró el primer video")
        }

        Thread.sleep(1000) // Esperar tras seleccionar el video

        val repeatCount = 3 // Número de videos que se quieren procesar

        for (i in 1..repeatCount) {
            Log.d("TikTokAutomation", "Procesando video $i")

            // Buscar el botón de "Me gusta" (corazón)
            val likeButton = device.findObject(
                UiSelector().resourceId("com.zhiliaoapp.musically:id/dwu")
            )

            if (likeButton.exists() && likeButton.isEnabled) {
                Log.d("TikTokAutomation", "Botón de 'Me gusta' encontrado, seleccionando")
                likeButton.click()
            } else {
                Log.e("TikTokAutomation", "No se encontró el botón de 'Me gusta'")
            }

            Thread.sleep(2000)

            // Deslizar hacia arriba para pasar al siguiente video
            val startX = device.displayWidth / 2 // Mitad de la pantalla horizontal
            val startY = device.displayHeight * 3 / 4 // Comenzar desde 3/4 de la altura
            val endY = device.displayHeight / 4 // Terminar en 1/4 de la altura
            val duration = 10 // Duración del deslizamiento en milisegundos

            Log.d("TikTokAutomation", "Deslizando hacia arriba para el siguiente video")
            device.swipe(startX, startY, startX, endY, duration)

            // Esperar un momento después de deslizar para que el video cargue
            Thread.sleep(2000) // Ajustar el tiempo si es necesario
        }

        Log.d("TikTokAutomation", "Proceso completado")

        Log.d("TikTokAutomation", "Automatización completada")
    }
}
