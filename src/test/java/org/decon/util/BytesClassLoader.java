package org.decon.util;

public class BytesClassLoader extends ClassLoader {

    public Class<?> fromBytes(String name, byte[] b) throws ClassNotFoundException {
        return defineClass(name, b, 0, b.length);
    }

}
