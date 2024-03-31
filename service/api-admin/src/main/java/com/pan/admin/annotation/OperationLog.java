package com.pan.admin.annotation;

import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.operation.BusinessType;

import java.lang.annotation.*;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-31 16:49
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    BusinessType businessType();

    MethodEnum reqMethod();

    String reqModule();
}
