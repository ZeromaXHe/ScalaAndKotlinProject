package com.zerox.coroutine

import kotlinx.coroutines.*

val symbol = arrayOf("-", "\\", "|", "/")

fun main() = runBlocking {
    var progress = 0
    var roll = 0
    launch {
        while (progress < 100) {
            delay(100L)
            printProgressBar(progress, roll)
        }
        printProgressBar(progress, roll)
    }
    launch {
        while (progress < 100) {
            delay(250L)
            roll = (roll + 1) % 4
        }
    }
    launch {
        while (progress < 100) {
            delay(100L)
            progress += 1
        }
    }
    println("Command line progress bar")
}

private fun printProgressBar(progress: Int, roll: Int) {
    print("\r${"%-3d".format(progress)}/100 ${symbol[roll]} ")
    for (i in 1..progress) {
        print("█")
    }
    for (i in 100 downTo progress + 1) {
        print("-")
    }
}