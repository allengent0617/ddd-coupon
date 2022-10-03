package com.allen.coupon.common.util;

import com.allen.coupon.common.exception.ValidationException;

/**
 * 校验工具类
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */
public class ValidationUtil {

    public static void isTrue(boolean expect, String code, Object... params) {
        if (!expect) {
            throw ValidationException.of(code, params);
        }
    }

    public static void isFalse(boolean expect, String code, Object... params) {
        isTrue(!expect, code, params);
    }

}