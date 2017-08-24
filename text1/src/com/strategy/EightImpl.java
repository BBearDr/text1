package com.strategy;

/**
 * @Description:
 * @Date:2017/6/29 9:33
 * @Author:cjx
 */
public class EightImpl implements StrategyIn {

    @Override
    public double dealModel(int InputMoney) {
        double result = InputMoney*0.8;
        return result;
    }
}
