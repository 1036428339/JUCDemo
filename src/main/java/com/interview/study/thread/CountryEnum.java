package com.interview.study.thread;

import lombok.Getter;

/**
 * @author liwenchang
 * @create 2020-11-03 20:34
 */
public enum CountryEnum {
    ONE(0,"齐","亚理士多德"),TOW(2,"楚","Plato"),THREE(3,"燕","Socratos"),FOUR(4,"赵","孔子"),FIVE(5,"韩","张三"),SIX(1,"魏","李四");

    @Getter private Integer retCode;
    @Getter private String retMessage;
    @Getter private String name;
    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }
    CountryEnum(Integer retCode, String retMessage,String name) {
        this.retCode = retCode;
        this.retMessage = retMessage;
        this.name=name;
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
