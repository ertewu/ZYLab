package zystudio.cases.javabase.annotation_fruit;

/**
 * Created by leeco on 2017/6/9.
 */

public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor= FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id=1,name="红富士",address ="陕西省" )
    private String appleProvider;

    public String getAppleName() {
        return appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public void displayName() {
        System.out.println("水果名字：红富士");
    }
}
