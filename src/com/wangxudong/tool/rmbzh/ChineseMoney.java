package com.wangxudong.tool.rmbzh;

import java.math.BigDecimal;



public class ChineseMoney {

    public static String getChineseMoney(String sNumber) {
        if(sNumber == null || "0".equals(sNumber)){
            return "��Բ��";
        }
        float number = Float.parseFloat(sNumber);
        if(number == 0){
            return "��Բ��";
        }
        char[] hunit = { 'ʰ', '��', 'Ǫ' }; // ����λ�ñ�ʾ
        char[] vunit = { '��', '��' }; // ������ʾ
        char[] digit = { '��', 'Ҽ', '��', '��', '��', '��', '½', '��', '��', '��' }; // ���ֱ�ʾ
        BigDecimal b = new BigDecimal(sNumber);
        b = b.multiply(new BigDecimal(100));
        long midVal = b.longValue();//(long) (number * 100); // ת��������
        String valStr = String.valueOf(midVal); // ת�����ַ���
        if(midVal < 10){
            return digit[(int)midVal] + "��";
        }
        String head = valStr.substring(0, valStr.length() - 2); // ȡ��������
        String rail = valStr.substring(valStr.length() - 2); // ȡС������

        String prefix = ""; // ��������ת���Ľ��
        String suffix = ""; // С������ת���Ľ��
        // ����С����������
        if (rail.equals("00")) { // ���С������Ϊ0
            suffix = "��";
        } else {
        	if(rail.charAt(1)=='0'){
        		suffix = digit[rail.charAt(0) - '0'] + "����";
        	}else{
        		suffix = digit[rail.charAt(0) - '0'] + (rail.charAt(0)=='0'?"":"��")
                    	+ digit[rail.charAt(1) - '0'] + "��";
        	}// ����ѽǷ�ת������
        }
        // ����С����ǰ�����
        char[] chDig = head.toCharArray(); // ����������ת�����ַ�����
        char zero = '0'; // ��־'0'��ʾ���ֹ�0
        byte zeroSerNum = 0; // ��������0�Ĵ���
        for (int i = 0; i < chDig.length; i++) { // ѭ������ÿ������
            int idx = (chDig.length - i - 1) % 4; // ȡ����λ��
            int vidx = (chDig.length - i - 1) / 4; // ȡ��λ��
            if (chDig[i] == '0') { // �����ǰ�ַ���0
                zeroSerNum++; // ����0��������
                if (zero == '0') { // ��־
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                //���ε����һλ�պ����㲢�������ظ�
                if(idx == 0 && vidx >= 1 && zeroSerNum == 1){
                    prefix += vunit[vidx - 1]; // �ν���λ��Ӧ�ü��϶�������,��
                }
                //���ε����һλ�պ����㲢�������ظ�
                //���Һ���һ��������
                if(idx == 0 && vidx >= 1 && zeroSerNum > 1){
                    if(chDig[i] == '0' && i < chDig.length && chDig[i + 1] != '0')
                        prefix += "��";
                }
                
                continue;
            }
            zeroSerNum = 0; // ����0��������
            if (zero != '0') { // �����־��Ϊ0,�����,������,��ʲô��
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // ת�������ֱ�ʾ
            if (idx > 0)
                prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // �ν���λ��Ӧ�ü��϶�������,��
            }
        }

        if (prefix.length() > 0)
            prefix += 'Բ'; // ����������ִ���,����Բ������
        return prefix + suffix; // ������ȷ��ʾ
    }
}

/**
 * Copyright (c) 2005,��˰�ܾ�&������ͨ�Ƽ� All rights reserved.
 */
