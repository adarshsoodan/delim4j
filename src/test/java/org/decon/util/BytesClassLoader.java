package org.decon.util;

public class BytesClassLoader extends ClassLoader {

    public static final BytesClassLoader singleton = new BytesClassLoader();
    
    public Class<?> fromBytes(String name, byte[] b) throws ClassNotFoundException {
        return defineClass(name, b, 0, b.length);
    }

}
