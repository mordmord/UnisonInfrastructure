package com.one20.unisoninfrastructure.logging

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter


/**
 * Logger implementation for writing logs to a file
 */
open class UnisonFileLogger(filePath: String) : UnisonLogger() {

    /**
     * Path to local log file
     */
    private var mFilePath: String? = null

    /**
     * Actual log file
     */
    private var mFile: File? = null

    /**
     * Writer instances for writing to file
     */
    private var printWriter: PrintWriter? = null
    private var fileWriter: FileWriter? = null
    private var bufferedWriter: BufferedWriter? = null


    init {
        this.mFilePath = filePath
        this.mFile = File(this.mFilePath)
    }

    /**
     * Checks file existence and creates new file if doesn't already exist
     */
    private fun checkInit() {
        mFile?.let {
            if(!mFile!!.exists()) {
                mFile!!.createNewFile()
            }
        }

        fileWriter = FileWriter(mFile, true)
        bufferedWriter = BufferedWriter(fileWriter)
        printWriter = PrintWriter(bufferedWriter)
    }


    override fun log(level: Int, message: String) {
        checkInit()

        printWriter?.println(message)
        close()
    }


    override fun log(level: Int, throwable: Throwable) {
        checkInit()

        printWriter?.println(throwable.toString())
        close()
    }

    private fun close() {
        printWriter?.close()
        bufferedWriter?.close()
        fileWriter?.close()
    }
}