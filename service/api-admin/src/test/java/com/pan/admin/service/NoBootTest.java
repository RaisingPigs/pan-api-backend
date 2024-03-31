package com.pan.admin.service;

import com.pan.model.req.user.UserLoginReq;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-06 12:17
 **/
public class NoBootTest {
    @Test
    void test() {
        UserLoginReq userLoginReq = new UserLoginReq();
        userLoginReq.setUsername("212313");

        EvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("userLoginReq", userLoginReq);

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#userLoginReq.getUsername()");

        String value = (String) expression.getValue(ctx);

        System.out.println(value);
    }
}
