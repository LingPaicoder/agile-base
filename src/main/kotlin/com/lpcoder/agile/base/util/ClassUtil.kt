package com.lpcoder.agile.base.util

import java.io.File
import java.net.JarURLConnection
import java.net.URL

object ClassUtil {

    private val PATH_SEPARATOR = "/"
    private val PACKAGE_SEPARATOR = "."

    // todo: study and optimize
    fun getDefaultClassLoader(): ClassLoader {
        return Thread.currentThread().contextClassLoader
    }

    fun loadClass(type: String, classLoader: ClassLoader = getDefaultClassLoader()): Class<*> =
            when (type) {
                "byte" -> Byte::class.java
                "short" -> Short::class.java
                "int" -> Int::class.java
                "long" -> Long::class.java
                "float" -> Float::class.java
                "double" -> Float::class.java
                "char" -> Char::class.java
                "boolean" -> Boolean::class.java
                else -> classLoader.loadClass(type)
            }

    fun getClassSet(packageName: String): Set<Class<*>> =
            getDefaultClassLoader().getResources(packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR))
                    .toList().map { getClassSet(it) }.flatMap { it }.toSet()


    private fun getClassSet(url: URL): Set<Class<*>> =
            when (url.protocol) {
                "file" -> getClassSetByFileProtocolUrl(url)
                "jar" -> getClassSetByJarProtocolUrl(url)
                else -> throw IllegalArgumentException("unknown url protocol: ${url.protocol}. support only 'file' or 'jar'")
            }

    private fun getClassSetByJarProtocolUrl(url: URL): Set<Class<*>> {
        if (url.protocol != ("jar")) {
            throw IllegalArgumentException("found url protocol: ${url.protocol}. support only 'jar'")
        }
        val jarURLConnection = url.openConnection() as JarURLConnection
        return jarURLConnection.jarFile.entries().toList()
                .filter { it.name.endsWith(".class") }
                .map { it.name.substring(0, it.name.lastIndexOf(".")).replace("/", ".") }
                .map { Class.forName(it, false, getDefaultClassLoader()) }
                .toSet()
    }

    private fun getClassSetByFileProtocolUrl(url: URL): Set<Class<*>> {
        if (url.protocol != ("file")) {
            throw IllegalArgumentException("found url protocol: ${url.protocol}. support only 'file'")
        }
        val packagePath = url.path.replace("%20", " ")
        val classSet = mutableSetOf<Class<*>>()
        addClass(classSet, packagePath)
        return classSet
    }

    private fun addClass(classSet: MutableSet<Class<*>>, packagePath: String) {
        File(packagePath).listFiles { file ->
            (file.isFile && file.name.endsWith(".class")) || file.isDirectory
        }.toList().forEach {
            if (it.isFile) {
                val classSimpleName = it.name.substring(0, it.name.lastIndexOf("."))
                val className = packagePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR) + classSimpleName
                classSet.add(Class.forName(className, false, getDefaultClassLoader()))
            } else {
                val subPackagePath = packagePath + PATH_SEPARATOR + it.name
                addClass(classSet, subPackagePath)
            }
        }
    }

}