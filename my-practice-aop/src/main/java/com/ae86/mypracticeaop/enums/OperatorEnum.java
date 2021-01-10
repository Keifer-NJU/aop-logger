package com.ae86.mypracticeaop.enums;

/**
 * @author Keifer
 */
public enum OperatorEnum {
    GT(">"), LT("<"),
    EQ("="), NOTEQ("!="),
    IN("in"), NOTIN("notin"),
    YU("&"),REGS("("),REGE(")")
    ;

    private String operator;
    OperatorEnum(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
