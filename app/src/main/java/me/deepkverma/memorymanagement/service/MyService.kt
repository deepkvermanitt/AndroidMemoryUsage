package me.deepkverma.memorymanagement.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.deepkverma.memorymanagement.logs.MemoryLogs.Companion.TAG
import me.deepkverma.memorymanagement.utils.Utils

class MyService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.IO)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand ***************************")
        serviceScope.launch {
            Utils.loadItemsInList("MyService")
        }
//        stopSelf()
        return START_REDELIVER_INTENT

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}