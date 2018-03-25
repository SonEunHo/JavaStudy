package patterns.BuilderClassPattern;

import java.util.Date;

/**
 * Created by Nano.son on 2018. 3. 15.
 */
public class FoodProductTest {
    public static void main(String[] args) {
        FoodProduct.Builder builder =
                new FoodProduct.Builder("chicken", new Date())
                .calorie(540)
                .fat(30)
                .protein(50)
                .carbohydrate(23);

        FoodProduct chicken = builder.build();
        System.out.println(chicken);

        FoodProduct yogurt = (new FoodProduct.Builder("yogurt", new Date())
                .calorie(230)
                .fat(30)
                .protein(12)
                .carbohydrate(20)).build();

        System.out.println(yogurt);
    }
}
