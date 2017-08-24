package com.strategy;

import java.util.Scanner;

/**
 * Description: 策略模式。不同场合下要求实现不同的方案
 * Date:2017/6/29 9:19
 * Author:cjx
 */
public class StrategyMain {
    public static void main(String[] args) {
//        Scanner scanner = System.
      /*  String discountType = "打八折";
        StrategyType strategyType = new StrategyType();
        strategyType.setType(discountType);
        double result = strategyType.dealResult(100);*/
        StrategyType strategyType = new StrategyType(new EightImpl());
        double result = strategyType.operator(100);
        System.out.println(result);
    }
}
