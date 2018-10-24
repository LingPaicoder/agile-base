package com.lpcoder.agile.base.util

import java.io.File
import java.net.JarURLConnection
import java.net.URL

object ClassUtil {

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
            getDefaultClassLoader().getResources(packageName.replace(".", "/"))
                    .toList().map { getClassSet(it) }.flatMap { it }.toSet()


    private fun getClassSet(url: URL): Set<Class<*>> =
            when (url.protocol) {
                "file" -> getClassSetByFileProtocolUrl(url)
                "jar" -> getClassSetByJarProtocolUrl(url)
                else -> throw IllegalArgumentException("unknown url protocol: ${url.protocol}. support only 'file' or 'jar'")
            }


    private fun getClassSetByFileProtocolUrl(url: URL): Set<Class<*>> {
        if (url.protocol != ("file")) {
            throw IllegalArgumentException("found url protocol: ${url.protocol}. support only 'file'")
        }
        val packagePath = url.path.replace("%20", " ")
        File(packagePath).listFiles { file ->
            (file.isFile && file.name.endsWith(".class")) || file.isDirectory
        }.toList()
        return emptySet()
    }

    private fun addClass(classSet: Set<Class<*>>, packagePath: String, packageName: String) {

    }


    /*private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }*/


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

    /*public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }*/


}