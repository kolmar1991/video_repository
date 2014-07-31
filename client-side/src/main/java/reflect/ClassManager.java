package reflect;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author piotrek
 */
public class ClassManager {

    /*
        client provides:
            - jar file with all necessary conversion logic
            - name of the jar file
            - name of the class where convert method is defined
        following method loads class where convert method is defined, in order to invoke the method
        during coversion process
    */
    private static Class load(String jarPath, String classNameToLoad) throws IOException, ClassNotFoundException {
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> entries = jarFile.entries();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new URL("jar:file:" + jarPath + "!/")});

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                continue;
            }
            String className = entry.getName().substring(0, entry.getName().length() - 6).replace('/', '.');
            if (className.equals(classNameToLoad)) {
                return classLoader.loadClass(className);
            }
        }

        /*
            some exception message will be added
            Piter: I'm thinking about adding some property bundle for managing exceptiom messages
            and other configuration stuff
         */
        throw new ClassNotFoundException();
    }
    
    public static String invoke(String jarPath, String classNameToLoad, String methodName, Object object) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException {
        Class clazz = load(jarPath, classNameToLoad);
        return (String) clazz.getMethod(methodName).invoke(object);
    }
    
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, ClassNotFoundException {
        System.out.println(invoke("ConverterExample-1.0-SNAPSHOT.jar", "main.MainClass", "convert", null));
    }
}
