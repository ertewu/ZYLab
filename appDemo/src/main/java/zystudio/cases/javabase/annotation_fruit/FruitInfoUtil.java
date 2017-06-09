package zystudio.cases.javabase.annotation_fruit;

import android.util.Log;

import java.lang.reflect.Field;

import zystudio.mylib.utils.LogUtil;

public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz){

        String strFruitName="Fruit Name:";
        String strFruitColor="Fruit Color:";
        String strFruitProvicer="供应商信息";

        Field[] fields=clazz.getDeclaredFields();

        for( Field field: fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName=(FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
            }
            else if( field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor=(FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                LogUtil.log(strFruitColor);
            }
            else if ( field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider=(FruitProvider) field.getAnnotation(FruitProvider.class);
                LogUtil.log(fruitProvider.id()+"|"+fruitProvider.name()+"|"+fruitProvider.address());
            }

        }
    }
}
