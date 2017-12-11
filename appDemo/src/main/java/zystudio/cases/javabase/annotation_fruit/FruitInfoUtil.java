package zystudio.cases.javabase.annotation_fruit;

import java.lang.reflect.Field;

import zystudio.mylib.utils.LogUtil;

public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz){

        String strFruitName;
        String strFruitColor;

        Field[] fields=clazz.getDeclaredFields();

        for( Field field: fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName=(FruitName) field.getAnnotation(FruitName.class);
                strFruitName=fruitName.value();
                LogUtil.log("strFruitName:"+strFruitName);
            }
            else if( field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor=(FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=fruitColor.fruitColor().toString();
                LogUtil.log("strFruitColor:"+strFruitColor);
            }
            else if ( field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider=(FruitProvider) field.getAnnotation(FruitProvider.class);
                LogUtil.log("All:"+fruitProvider.id()+"|"+fruitProvider.name()+"|"+fruitProvider.address());
            }

        }
    }
}
