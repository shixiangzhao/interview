package com.shixzh.java.interview.pattern;

/**
 * 第2条： 遇到多个构造器参数时要考虑用Builder
 * 静态工厂和构造器有个共同的局限性：它们都不能很好的扩展到大量的可选参数。
 * 重叠构造器模式可行，但是当有许多参数时，客户端代码会很难编写。
 * 
 * @author shixiang.zhao
 */
public class NutritionFacts {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}

/**
 * JavaBean因为构造过程被分到了几个调用中，创建实例很容易，
 * 但是在构造过程中，可能处于不一致的状态，阻止了把类做成不可变的可能，需要确保它线程安全。
 * 
 * @author shixiang.zhao
 */
class NutritionFacts1 {

    private int servingSize = -1;
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutritionFacts1() {}

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts1 cocaCola = new NutritionFacts1();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }
}

/**
 * builder模式模拟了具名的可选参数
 * 
 * @author shixiang.zhao
 */
class NutritionFacts2 {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class NutritionFacts2Builder implements Builder<NutritionFacts2> {

        //Required Parameters
        private final int servingSize;
        private final int servings;
        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public NutritionFacts2Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public NutritionFacts2Builder calories(int val) {
            calories = val;
            return this;
        }

        public NutritionFacts2Builder fat(int val) {
            fat = val;
            return this;
        }

        public NutritionFacts2Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts2Builder sodium(int val) {
            sodium = val;
            return this;
        }

        @Override
        public NutritionFacts2 build() {
            return new NutritionFacts2(this);
        }
    }

    private NutritionFacts2(NutritionFacts2Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts2 cocaCola = new NutritionFacts2.NutritionFacts2Builder(240, 8).calories(100).sodium(25)
                .carbohydrate(27)
                .build();

    }
}

interface Builder<T> {

    public T build();

}
