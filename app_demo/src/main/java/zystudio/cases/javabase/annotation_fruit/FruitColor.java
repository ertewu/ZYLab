package zystudio.cases.javabase.annotation_fruit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by leeco on 2017/6/9.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    public enum Color {BLUE,RED,GREEN}

    Color fruitColor() default Color.GREEN;

}
