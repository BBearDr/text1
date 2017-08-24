package com.strategy;

/**
 * Description:决定调用哪个处理方式
 * Date:2017/6/29 9:41
 * Author:cjx
 */
public class StrategyType {
    /*private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double dealResult(int money) {
        double result = 0;
        if (this.type.equals("打八折")) {
            StrategyIn strategyIn = new EightImpl();
            result = strategyIn.dealModel(money);
        }
        return result;
   }*/
    private StrategyIn strategyIn;

    public StrategyIn getStrategyIn() {
        return strategyIn;
    }

    public void setStrategyIn(StrategyIn strategyIn) {
        this.strategyIn = strategyIn;
    }

    public StrategyType(StrategyIn strategyIn) {
        this.strategyIn = strategyIn;
    }

    public double operator(int money) {
        return this.strategyIn.dealModel(money);
    }
}
