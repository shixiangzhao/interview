package com.shixzh.java.interview.basic;

import java.math.BigDecimal;

/**
 * 如果数值范围不超过9位十进制数字，就可以使用int，如果不超过18位数字，就可以用long，
 * 如果数字可能超过18位，就必须使用BigDecimal
 * @author shixiang.zhao
 *
 */
public class TestFloat {

    public static void main(String[] args) {
        double f = 3 * 0.1;
        System.out.println(f);
        System.out.println(3 * 0.1 == 0.3);
        System.out.println(1.03 - .42);
        System.out.println(1.00 - 9 * .10);
        //buyCandy1();
        //buyCandy2();
        buyCandy3();
    }

    public static void buyCandy1() {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = .10; funds >= price; price += .10) {
            funds -= price;
            itemsBought++;
        }
        //3 items bought.
        System.out.println(itemsBought + " items bought.");
        //Change: $0.3999999999999999
        System.out.println("Change: $" + funds);
    }

    //BigDecimal 很慢而且不方便
    public static void buyCandy2() {
        //需要带引号，不然还是当作float计算
        BigDecimal funds = new BigDecimal("1.00");
        int itemsBought = 0;
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            //System.out.println("price " + price);
            itemsBought++;
            funds = funds.subtract(price);
        }
        //4 items bought.
        System.out.println(itemsBought + " items bought.");
        //Change: $0.00
        System.out.println("Change: $" + funds);
    }

    //user int instead of float
    public static void buyCandy3() {
        int funds = 100;
        int itemsBought = 0;
        for (int price = 10; funds >= price; price += 10) {
            //System.out.println("price " + price);
            itemsBought++;
            funds -= price;
        }
        //4 items bought.
        System.out.println(itemsBought + " items bought.");
        //Change: $0.00
        System.out.println("Change: $" + funds);
    }

}
