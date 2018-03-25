package patterns.BuilderClassPattern;

import java.util.Date;

/**
 * Created by Nano.son on 2018. 3. 15.
 * 빌더 클래스는 인스턴스 변수가 많고, 필수 파라미터 외에도 다양한 필드를 설정 가능할 때 유용하다.
 * 예를 들어 new MyClass("Jack", 23, "NY", "Soccer", "Job Seeker"); 와 같은 객체 생성은 각 파라미터가 의미하는 바를 한번에 캐치하기 힘들다.
 * 만약 파라미터가 훨씬 더 많다면 골치 아플 것이다.
 *
 * Builder 클래스를 사용하면 기본적인 파라미터만 세팅한 후, chain 형식으로 각 파라미터를 세팅하여, 가독성이 좋아진다.
 * ex. MyClass.builder("Jack", 23).city("NY").hobby("Soccer").Job("Job Seeker");
 * 위의 생성자가 아래와 같이 바뀔 수 있다.
 */
public class FoodProduct {
    private String name;
    private Date expirationDate;
    private int calorie=0;
    private int fat=0;
    private int protein=0;
    private int carbohydrate=0;

    public static class Builder {
        String name;
        Date expirationDate;
        int calorie;
        int fat;
        int protein;
        int carbohydrate;

        public Builder(String name, Date expirationDate) {
            this.name = name;
            this.expirationDate = expirationDate;
        }
        public Builder calorie(int calorie) {
            this.calorie = calorie;
            return this;
        }
        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }
        public Builder protein(int protein) {
            this.protein = protein;
            return this;
        }
        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public FoodProduct build() {
            return new FoodProduct(this);
        }
    }

    private FoodProduct(Builder builder) {
        name = builder.name;
        expirationDate = builder.expirationDate;
        calorie = builder.calorie;
        fat = builder.fat;
        protein = builder.protein;
        carbohydrate = builder.carbohydrate;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "name='" + name + '\'' +
                ", expirationDate=" + expirationDate +
                ", calorie=" + calorie +
                ", fat=" + fat +
                ", protein=" + protein +
                ", carbohydrate=" + carbohydrate +
                '}';
    }
}
