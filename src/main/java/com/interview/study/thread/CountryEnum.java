package com.interview.study.thread;

import lombok.Getter;

/**
 * @author liwenchang
 * @create 2020-11-03 20:34
 */
public enum CountryEnum {
    ONE(1,"齐"),TOW(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"韩"),SIX(0,"魏");

    @Getter private Integer retCode;
    @Getter private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(index == value.getRetCode()){
                return value;
            }
        }
        return null;
    }
}
