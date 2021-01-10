package com.ae86.mypracticeaop.service.paramvalid;

import com.ae86.mypracticeaop.domain.RequestLogicBean;
import com.ae86.mypracticeaop.enums.ErrorReturnModel;
import com.ae86.mypracticeaop.enums.OperatorEnum;
import com.ae86.mypracticeaop.exceptions.NotSupportValidTypeException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Keifer
 */

@Service
public class ValidParamHelperService {

    @Resource
    private ValidParamFactory validParamFactory;

    /**
     * 验证错误时的返回类型
     *
     * @param returnModelValue
     * @throws NotSupportValidTypeException
     */
    public void checkReturnModel(int returnModelValue) throws NotSupportValidTypeException {
        if (returnModelValue > ErrorReturnModel.THROWEXCEPTION.getModel()) {
            throw new NotSupportValidTypeException("not support the error type return!");
        }
    }

    /**
     * 验证参数表达式
     *
     * @param validArray
     * @throws NotSupportValidTypeException
     */
    public void checkValidValue(String[] validArray) throws NotSupportValidTypeException {
        for (String validValue : validArray) {
            if (StringUtils.isBlank(validValue)) {
                continue;
            }
            if (check(validValue.trim())) {
                throw new NotSupportValidTypeException("not support the error type return!");
            }
        }
    }

    public boolean check(String validValue) {
        return !validValue.startsWith(OperatorEnum.EQ.getOperator()) &&
                !validValue.startsWith(OperatorEnum.NOTEQ.getOperator()) &&
                !validValue.startsWith(OperatorEnum.GT.getOperator()) &&
                !validValue.startsWith(OperatorEnum.LT.getOperator()) &&
                !validValue.startsWith(OperatorEnum.IN.getOperator()) &&
                !validValue.startsWith(OperatorEnum.NOTIN.getOperator()) &&
                !validValue.startsWith(OperatorEnum.REGS.getOperator());
    }

    /**
     * 验证参数是否为空
     *
     * @param args
     * @return
     */
    private boolean validNotNull(Object[] args) {
        for (Object arg : args) {
            if (arg == null || StringUtils.isBlank(arg.toString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证输入参数
     *
     * @param baseBusLogicBean
     * @param args
     * @return
     */
    public boolean validInputParam(RequestLogicBean baseBusLogicBean, Object[] args) {
        if (args == null || args.length == 0) {
            return true;
        }

        if (baseBusLogicBean.isParamNotNull() == false) {
            return true;
        }
        //简单对入参进行非空判断
        if (!validNotNull(args)) {
            return false;
        }

        String[] validModel = baseBusLogicBean.getValidModel();
        Object arg;
        for (int i = 0; i < validModel.length; i++) {
            String value = validModel[i].trim();
            arg = args[i];
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            boolean b = validParamFactory.validAll(value, arg);
            if (!b) {
                return false;
            }
        }
        return true;
    }


}

