package com.logistics.utils;

/**
 * 邮费算法
 *
 * @author keke
 * @date 2020/6/10
 */
public class LogisticsPriceUtil {

    private static final int STANDARD_WEIGHT = 12; // 标重
    private static final Double WEIGHT_UNIT_COST = 4.00; // 重量单价
    private static final Double DISTANCE_UNIT_COST = 0.05; // 距离单价
    private static final Double CONTINUED_WEIGHT = 3.00; // 续重价格

    /**
     * 总邮费计算算法
     *
     * @param weight （单位：Kg)
     * @param distance （单位：Km)
     */
    public static Double cost(Double weight, Double distance) {
        // 如果重量小于标准重量的话
        if (weight <= STANDARD_WEIGHT) {
            // 货物重量*重量单价 + 距离*距离单价
            return Math.ceil(weight*WEIGHT_UNIT_COST + distance*DISTANCE_UNIT_COST);
        } else {
            // 如果重量超过了标准重量的话，重量*标准重量 + 超出的重量*超出部分重量价格 + 距离*距离单价
            return Math.ceil(STANDARD_WEIGHT*WEIGHT_UNIT_COST
                    + (weight-STANDARD_WEIGHT)*CONTINUED_WEIGHT
                    + distance*DISTANCE_UNIT_COST);
        }
    }

}