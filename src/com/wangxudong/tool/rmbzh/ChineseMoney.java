package com.wangxudong.tool.rmbzh;

import java.math.BigDecimal;



public class ChineseMoney {

    public static String getChineseMoney(String sNumber) {
        if(sNumber == null || "0".equals(sNumber)){
            return "零圆整";
        }
        float number = Float.parseFloat(sNumber);
        if(number == 0){
            return "零圆整";
        }
        char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
        char[] vunit = { '万', '亿' }; // 段名表示
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
        BigDecimal b = new BigDecimal(sNumber);
        b = b.multiply(new BigDecimal(100));
        long midVal = b.longValue();//(long) (number * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串
        if(midVal < 10){
            return digit[(int)midVal] + "分";
        }
        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
        	if(rail.charAt(1)=='0'){
        		suffix = digit[rail.charAt(0) - '0'] + "角整";
        	}else{
        		suffix = digit[rail.charAt(0) - '0'] + (rail.charAt(0)=='0'?"":"角")
                    	+ digit[rail.charAt(1) - '0'] + "分";
        	}// 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                //区段的最后一位刚好是零并且零无重复
                if(idx == 0 && vidx >= 1 && zeroSerNum == 1){
                    prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
                }
                //区段的最后一位刚好是零并且零有重复
                //并且后面一个不是零
                if(idx == 0 && vidx >= 1 && zeroSerNum > 1){
                    if(chDig[i] == '0' && i < chDig.length && chDig[i + 1] != '0')
                        prefix += "零";
                }
                
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0)
                prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0)
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        return prefix + suffix; // 返回正确表示
    }
}

/**
 * Copyright (c) 2005,国税总局&安徽皖通科技 All rights reserved.
 */
