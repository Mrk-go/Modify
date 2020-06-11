package com.k.go

import java.io.*

object ModifyText {
    fun modifyTextAndroidSupportText(file: File) {
        val bufferedReader = BufferedReader(FileReader(file))
        val stringBuilder = StringBuilder()
        kotlin.runCatching {
            var tempStr = bufferedReader.readLine()
            var modify = false
            while (tempStr != null) {
                if (tempStr.contains("android.support.v7")) {
                    modify = true
                    tempStr = tempStr.replace("android.support.v7", "androidx.appcompat")
                }
                stringBuilder.append(tempStr).append("\n")
                tempStr = bufferedReader.readLine()
            }
            bufferedReader.close()
            if (modify) {
                println(stringBuilder)
                val fileWriter = FileWriter(file)
                fileWriter.write(stringBuilder.toString())
                fileWriter.close()
            }

        }
    }

    fun modifyTextBaseCoreFragmentPackage(file: File) {
        val bufferedReader = BufferedReader(FileReader(file))
        val stringBuilder = StringBuilder()
        kotlin.runCatching {
            var tempStr = bufferedReader.readLine()
            var modify = false
            while (tempStr != null) {
                if (tempStr.contains("com.cnksi.core.fragment.BaseCoreFragment")) {
                    modify = true
                    tempStr = tempStr.replace("com.cnksi.core.fragment.BaseCoreFragment", "com.cnksi.common.base.BaseCoreFragment")
                }
                stringBuilder.append(tempStr).append("\n")
                tempStr = bufferedReader.readLine()
            }
            bufferedReader.close()
            if (modify) {
                println(stringBuilder)
                val fileWriter = FileWriter(file)
                fileWriter.write(stringBuilder.toString())
                fileWriter.close()
            }

        }
    }
}

fun main() {
    modifyActivityFile()
}

fun modifyLayoutFile(){
    getFileIterator("") {
        it.isFile && it.absolutePath.contains("res") && it.absolutePath.contains("layout") && !it.absolutePath.contains(
            "build"
        )
    }.forEach {
        println(it.absolutePath)
        ModifyText.modifyTextAndroidSupportText(File(it.absolutePath))
    }
}

fun modifyActivityFile(){
    getFileIterator("") {
        it.isFile && (it.absolutePath.contains("activity") ||it.absolutePath.contains("fragment"))&& it.absolutePath.contains("java") && !it.absolutePath.contains(
            "build"
        )
    }.forEach {
        println(it.absolutePath)
        ModifyText.modifyTextBaseCoreFragmentPackage(File(it.absolutePath))
    }
}

fun getFileIterator(fileName: String, p: (File) -> Boolean): Sequence<File> {
    val file = File("E:\\learn\\erdos-android")
    return file.walk().filter(p)
}
