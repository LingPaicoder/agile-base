package com.lpcoder.agile.base.util

import java.io.File
import java.net.JarURLConnection
import java.net.URL

object ClassUtil {

    private val PATH_SEPARATOR = "/"
    private val PACKAGE_SEPARATOR = "."

    private val BASIC_TYPE_DEFAULT_VALUE_MAP = mapOf<Class<*>, Any>(
            Byte::class.java to 0.toByte(),
            Short::class.java to 0.toShort(),
            Int::class.java to 0,
            Long::class.java to 0.toLong(),
            Float::class.java to 0.0.toFloat(),
            Double::class.java to 0.0,
            Char::class.java to ' ',
            Boolean::class.java to false)

    // todo: study and optimize
    fun getDefaultClassLoader(): ClassLoader {
        return Thread.currentThread().contextClassLoader
    }

    fun isBasicType(clazz: Class<*>) = BASIC_TYPE_DEFAULT_VALUE_MAP.keys.contains(clazz)

    fun getBasicTypeDefaultValue(clazz: Class<*>) = if (isBasicType(clazz)) {
        BASIC_TYPE_DEFAULT_VALUE_MAP[clazz]
    } else {
        throw IllegalArgumentException("$clazz is not basic type")
    }

    fun loadClass(type: String, classLoader: ClassLoader = getDefaultClassLoader()): Class<*> =
            when (type) {
                "byte" -> Byte::class.java
                "short" -> Short::class.java
                "int" -> Int::class.java
                "long" -> Long::class.java
                "float" -> Float::class.java
                "double" -> Double::class.java
                "char" -> Char::class.java
                "boolean" -> Boolean::class.java
                else -> classLoader.loadClass(type)
            }

    fun getClassSet(packageName: String): Set<Class<*>> {
        val urls = getDefaultClassLoader().getResources(packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR))
        while (urls.hasMoreElements()) {
            val url = urls.nextElement()
        }
        return getDefaultClassLoader().getResources(packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR))
                .toList().map { getClassSet(it, packageName) }.flatMap { it }.toSet()
    }


    private fun getClassSet(url: URL, packageName: String): Set<Class<*>> {
        return when (url.protocol) {
            "file" -> getClassSetByFileProtocolUrl(url, packageName)
            "jar" -> getClassSetByJarProtocolUrl(url)
            else -> throw IllegalArgumentException("unknown url protocol: ${url.protocol}. support only 'file' or 'jar'")
        }
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

    private fun getClassSetByFileProtocolUrl(url: URL, packageName: String): Set<Class<*>> {
        if (url.protocol != ("file")) {
            throw IllegalArgumentException("found url protocol: ${url.protocol}. support only 'file'")
        }
        val packagePath = url.path.replace("%20", " ")
        val classSet = mutableSetOf<Class<*>>()
        addClass(classSet, packagePath, packageName)
        return classSet
    }

    private fun addClass(classSet: MutableSet<Class<*>>, packagePath: String, packageName: String) {
        File(packagePath).listFiles { file ->
            (file.isFile && file.name.endsWith(".class")) || file.isDirectory
        }.toList().forEach {
            if (it.isFile) {
                val classSimpleName = it.name.substring(0, it.name.lastIndexOf("."))
                val className = packageName + PACKAGE_SEPARATOR + classSimpleName
                classSet.add(Class.forName(className, false, getDefaultClassLoader()))
            } else {
                val subPackagePath = packagePath + PATH_SEPARATOR + it.name
                val subPackageName = packageName + PACKAGE_SEPARATOR + it.name
                addClass(classSet, subPackagePath, subPackageName)
            }
        }
    }

}