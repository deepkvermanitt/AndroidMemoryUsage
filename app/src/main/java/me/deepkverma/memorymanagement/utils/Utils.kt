package me.deepkverma.memorymanagement.utils

import me.deepkverma.memorymanagement.logs.MemoryLogs.Companion.logCurrentMemoryProfile

class Utils {
    companion object {
        val items = arrayListOf<Int>()

        fun loadItemsInList(source: String) {
            logCurrentMemoryProfile("before adding items in list $source")

            for (i in 1..1000000) {
                items.add(i)
            }
            logCurrentMemoryProfile("After adding items in list $source")

        }
    }

}