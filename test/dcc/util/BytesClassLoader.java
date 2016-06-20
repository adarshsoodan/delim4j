package dcc.util;

public class BytesClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] b) throws ClassNotFoundException {
        return defineClass(name, b, 0, b.length);
    }

}
