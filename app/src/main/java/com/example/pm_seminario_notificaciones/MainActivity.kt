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
import com.example.pm_seminario_notificaciones.databinding.ActivityMainBinding
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object {
        val APP_ID = "com.example.pm_seminario_notificaciones"
        val CHANNEL_ID = "canal1"
        var id = AtomicInteger(0)

        fun create_notification_id(): Int {
            return id.incrementAndGet()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.generadorNotificacion.setOnClickListener {
            mostrar_notificacion(this)
        }
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
            .setContentText("Mi id es ${id.getAndIncrement()}")
            .setColor(ContextCompat.getColor(context, R.color.salmon))

        // Mostrar la notificacion
        with(context.getSystemService<NotificationManager>()) {
            this?.notify(id.toInt(), builder.build())
        }
    }
}