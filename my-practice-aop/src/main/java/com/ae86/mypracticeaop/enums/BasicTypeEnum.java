package com.ae86.mypracticeaop.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Keifer
 */
public enum BasicTypeEnum {
    INT("int"), INTEGER("Integer"),

    SHORT("short"), SHORT2("Short"),

    BYTE("byte"), BYTE2("Byte"),

    BOOLEAN("boolean"), BOOLEAN2("Boolean"),

    FLOAT("float"), FLOAT2("Float"),

    LONG("long"), LONG2("Long"),

    CHAR("char"), CHARACTER("Character"),

    STRING("String");
    private String type;

    BasicTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Set<String> getTypeSet(){
        Set<String> set = new HashSet<>();
        for (BasicTypeEnum basicTypeEnum : BasicTypeEnum.values()){
            set.add(basicTypeEnum.getType());
        }
        return set;
    }

}
