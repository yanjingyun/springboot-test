package com.yjy.util;

import com.yjy.controller.vo1.UserVo;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 校验工具类（手动）
 */
public class ValidatorUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> String validateEntity(T obj) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<T>> set = validator.validate(obj);
        if (!CollectionUtils.isEmpty(set)) {
            for (ConstraintViolation<T> cv : set) {
                stringBuilder.append(cv.getPropertyPath()).append(cv.getMessage()).append("，");
            }
        }

        return stringBuilder.length() == 0 ? "" : stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    // 测试
    public static void main(String[] args) {
        // 测试1
        UserVo userVo1 = new UserVo();
        System.out.println("userVo1校验：" + ValidatorUtil.validateEntity(userVo1));

        // 测试2
        UserVo userVo2 = new UserVo();
        userVo2.setAdress(new UserVo.Adress() {{
            setName("11");
        }});
        System.out.println("userVo2校验：" + ValidatorUtil.validateEntity(userVo2));

        // 测试3
        UserVo userVo3 = new UserVo();
        userVo3.setName("axxs");
        userVo3.setMobilePhone("1234566");
        userVo3.setAdress(new UserVo.Adress() {{
            setName("123");
        }});
        System.out.println("userVo3校验：" + ValidatorUtil.validateEntity(userVo3));
    }
}
