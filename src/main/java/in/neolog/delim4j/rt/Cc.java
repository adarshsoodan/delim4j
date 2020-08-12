/*
 * Copyright Adarsh Soodan, 2016
 * Licensed under http://www.apache.org/licenses/LICENSE-2.0
 */
package in.neolog.delim4j.rt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Cc {

    public static final String annotationDesc = "L" + Cc.class.getName().replace('.', '/') + ";";

}
