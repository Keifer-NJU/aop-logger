package com.ae86.mypracticeaop.service.paramvalid;

import com.ae86.mypracticeaop.enums.OperatorEnum;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Keifer
 */

@Component
public class ValidParamFactory {
    /**
     * 解析> 和>&<
     *
     * @param value 规则内容
     * @param arg   参数值
     * @return
     */
    private boolean validGT(final String value, Object arg) {
        String va = value.trim();
        boolean b = va.startsWith(OperatorEnum.GT.getOperator()) && !va.contains(OperatorEnum.YU.getOperator()) && !va.contains(OperatorEnum.LT.getOperator());
        if (b) {
            va = va.substring(1).trim();
            //int/Integer
            if (arg.getClass() == int.class || arg.getClass() == Integer.class) {
                int intValue = Integer.parseInt(va);
                int paramValue = Integer.parseInt(arg.toString());
                if (paramValue <= intValue) {
                    return false;
                }
            }
            //long/Long
            else if (arg.getClass() == long.class || arg.getClass() == Long.class) {
                long intValue = Long.parseLong(va);
                long paramValue = Long.parseLong(arg.toString());
                if (paramValue <= intValue) {
                    return false;
                }
            }
            //short/Short
            else if (arg.getClass() == short.class || arg.getClass() == Short.class) {
                short intValue = Short.parseShort(va);
                short paramValue = Short.parseShort(arg.toString());
                if (paramValue <= intValue) {
                    return false;
                }
            }
            //float/Float
            else if (arg.getClass() == float.class || arg.getClass() == Float.class) {
                float intValue = Float.parseFloat(va);
                float paramValue = Float.parseFloat(arg.toString());
                if (paramValue <= intValue) {
                    return false;
                }
            }
            //double/Double
            else if (arg.getClass() == double.class || arg.getClass() == Double.class) {
                double intValue = Double.parseDouble(va);
                double paramValue = Double.parseDouble(arg.toString());
                if (paramValue <= intValue) {
                    return false;
                }
            }
        }
        //解析 >&<
        else if (va.startsWith(OperatorEnum.GT.getOperator()) && va.contains(OperatorEnum.YU.getOperator()) && va.contains(OperatorEnum.LT.getOperator())) {
            String[] varray = va.split(OperatorEnum.YU.getOperator());
            String minValue = varray[0].trim().replace(OperatorEnum.GT.getOperator(), "");
            String maxValue = varray[1].trim().replace(OperatorEnum.LT.getOperator(), "");
            //int/Integer
            if (arg.getClass() == int.class || arg.getClass() == Integer.class) {
                int intMinValue = Integer.parseInt(minValue);
                int intMaxValue = Integer.parseInt(maxValue);
                int paramValue = Integer.parseInt(arg.toString());
                if ((paramValue <= intMinValue) || (paramValue >= intMaxValue)) {
                    return false;
                }
            }
            //long/Long
            else if (arg.getClass() == long.class || arg.getClass() == Long.class) {
                long longMinValue = Long.parseLong(minValue);
                long longMaxValue = Long.parseLong(maxValue);
                long paramValue = Long.parseLong(arg.toString());
                if ((paramValue <= longMinValue) || (paramValue >= longMaxValue)) {
                    return false;
                }
            }
            //short/Short
            else if (arg.getClass() == short.class || arg.getClass() == Short.class) {
                short shortMinValue = Short.parseShort(minValue);
                short shortMaxValue = Short.parseShort(maxValue);
                short paramValue = Short.parseShort(arg.toString());
                if ((paramValue <= shortMinValue) || (paramValue >= shortMaxValue)) {
                    return false;
                }
            }
            //float/Float
            else if (arg.getClass() == float.class || arg.getClass() == Float.class) {
                float shortMinValue = Float.parseFloat(minValue);
                float shortMaxValue = Float.parseFloat(maxValue);
                float paramValue = Float.parseFloat(arg.toString());
                if ((paramValue <= shortMinValue) || (paramValue >= shortMaxValue)) {
                    return false;
                }
            }
            //double/Double
            else if (arg.getClass() == double.class || arg.getClass() == Double.class) {
                double shortMinValue = Double.parseDouble(minValue);
                double shortMaxValue = Double.parseDouble(maxValue);
                double paramValue = Double.parseDouble(arg.toString());
                if ((paramValue <= shortMinValue) || (paramValue >= shortMaxValue)) {
                    return false;
                }
            }
        }
        //解析正则表达式
        else if (va.startsWith(OperatorEnum.REGS.getOperator()) && va.endsWith(OperatorEnum.REGE.getOperator())) {
            String reg = va.substring(1, va.length() - 1);
            return match(reg, arg.toString());
        }
        return true;
    }


    /**
     * 解析<
     *
     * @param value
     * @param arg
     * @return
     */
    private boolean validLT(final String value, final Object arg) {
        String va = value.trim();
        if (va.startsWith(OperatorEnum.LT.getOperator())) {
            va = va.substring(1).trim();
            if (arg.getClass() == int.class || arg.getClass() == Integer.class) {
                int intValue = Integer.parseInt(va);
                int paramValue = Integer.parseInt(arg.toString());
                if (paramValue >= intValue) {
                    return false;
                }
            } else if (arg.getClass() == long.class || arg.getClass() == Long.class) {
                long intValue = Long.parseLong(va);
                long paramValue = Long.parseLong(arg.toString());
                if (paramValue >= intValue) {
                    return false;
                }
            } else if (arg.getClass() == short.class || arg.getClass() == Short.class) {
                short shortValue = Short.parseShort(va);
                short paramValue = Short.parseShort(arg.toString());
                if (paramValue >= shortValue) {
                    return false;
                }
            } else if (arg.getClass() == float.class || arg.getClass() == Float.class) {
                float shortValue = Float.parseFloat(va);
                float paramValue = Float.parseFloat(arg.toString());
                if (paramValue >= shortValue) {
                    return false;
                }
            } else if (arg.getClass() == double.class || arg.getClass() == Double.class) {
                double shortValue = Double.parseDouble(va);
                double paramValue = Double.parseDouble(arg.toString());
                if (paramValue >= shortValue) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 解析=
     *
     * @param value
     * @param arg
     * @return
     */
    private boolean validEQ(final String value, Object arg) {
        String va = value.trim();
        if (va.startsWith(OperatorEnum.EQ.getOperator())) {
            va = va.substring(1).trim();
            if (!va.equals(arg.toString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解析!=
     *
     * @param value
     * @param arg
     * @return
     */
    private boolean validNOTEQ(final String value, Object arg) {
        String va = value.trim();
        if (va.startsWith(OperatorEnum.NOTEQ.getOperator())) {
            va = va.substring(2).trim();
            if (va.equals(arg.toString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解析in
     *
     * @param value
     * @param arg
     * @return
     */
    private boolean validIN(final String value, final Object arg) {
        String va = value.trim();
        if (va.startsWith(OperatorEnum.IN.getOperator())) {
            String[] var = getSplitArr(va);
            for (String v : var) {
                if (v.equals(arg.toString())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    /**
     * 解析in
     *
     * @param value
     * @param arg
     * @return
     */
    private boolean validNotIN(final String value, final Object arg) {
        String va = value.trim();
        if (va.startsWith(OperatorEnum.NOTIN.getOperator())) {
            String[] var = getSplitArr(va);
            for (String v : var) {
                if (v.equals(arg.toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 参数验证总入口
     *
     * @param value
     * @param arg
     * @return
     */
    public boolean validAll(final String value, final Object arg) {
        boolean gt = validGT(value, arg);
        boolean lt = validLT(value, arg);
        boolean eq = validEQ(value, arg);
        boolean noteq = validNOTEQ(value, arg);
        boolean in = validIN(value, arg);
        boolean notin = validNotIN(value, arg);
        boolean result = (gt == true) && (lt == true) && (eq == true) && (noteq == true) && (in == true) && (notin == true);
        return result;
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    private String[] getSplitArr(String var) {
        int s = var.indexOf("(");
        int e = var.lastIndexOf(")");
        String newVar = var.substring(s + 1, e);
        return newVar.split(",");
    }

}

