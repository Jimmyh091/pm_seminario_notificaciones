package com.example.pm_seminario_notificaciones

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mostrar_notificacion(this)
    }

    fun mostrar_notificacion(context: Context){

        // Crear el canal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "canal1"
            val channelName = "Canal de prueba"
            val notificationChannel  = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            context.getSystemService<NotificationManager>()?.createNotificationChannel(notificationChannel)
        }

        // Crear la notificacion
        val builder = NotificationCompat.Builder(context, "canal1")
            .setSmallIcon(R.drawable.pizza)
            .setContentTitle("Titulo de prueba")
            .setContentText("Texto de prueba")
            .setColor(ContextCompat.getColor(context, R.color.salmon))

        // Mostrar la notificacion
        with(context.getSystemService<NotificationManager>()) {
            this?.notify(1, builder.build())
        }
    }
}