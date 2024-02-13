package com.pan.gateway.check;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.pan.client.UserFeignClient;
import com.pan.model.constant.RedisConstant;
import com.pan.common.resp.BaseResponse;
import com.pan.common.util.BaseRespUtils;
import com.pan.common.util.SpringContextUtils;
import com.pan.model.req.user.AccessKeyReq;
import com.pan.sdk.constant.SignConstant;
import com.pan.sdk.util.SignUtils;
import lombok.Data;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-01-23 20:51
 **/
@Data
public class SignCheck {
    private static final long NONCE_EXPIRE_MINUTES = 2L;
    private String accessKey;
    private String secretKey;
    private String nonce;
    private long timestamp;
    private String sign;

    public SignCheck(String accessKey, String nonce, long timestamp, String sign) {
        this.accessKey = accessKey;
        this.nonce = nonce;
        this.timestamp = timestamp;
        this.sign = sign;
    }

    public boolean isValid() {
        return checkAccessKey()
            && checkNonce()
            && checkTimestamp()
            && checkSign();
    }

    private boolean checkAccessKey() {
        if (StrUtil.isBlank(accessKey)) {
            return false;
        }

        UserFeignClient userFeignClient = SpringContextUtils.getBean(UserFeignClient.class);
        BaseResponse<String> baseResponse = userFeignClient.getSecretKeyByAccessKey(new AccessKeyReq(accessKey));
        if (BaseRespUtils.isFailed(baseResponse)) {
            return false;
        }
        String secretKey = baseResponse.getData();
        if (StrUtil.isBlank(secretKey)) {
            return false;
        }

        this.secretKey = secretKey;
        return true;
    }

    private boolean checkNonce() {
        if (StrUtil.isBlank(nonce)) {
            return false;
        }

        int nonceInt = Integer.parseInt(nonce);
        if (nonceInt < SignConstant.NONCE_MIN_VALUE
            || nonceInt > SignConstant.NONCE_MAX_VALUE) {
            return false;
        }

        /*校验nonce长度, 以及看是否在redis中存在, 存在则抛异常, 不存在则保存到set集合中, 并加上过期时间*/
        StringRedisTemplate stringRedisTemplate = SpringContextUtils.getBean(StringRedisTemplate.class);
        BoundValueOperations<String, String> valueOps = stringRedisTemplate.boundValueOps(RedisConstant.NONCE_KEY + ":" + nonce);

        if (ObjectUtil.isNotNull(valueOps.get())) {
            return false;
        }

        valueOps.set(nonce, NONCE_EXPIRE_MINUTES, TimeUnit.MINUTES);
        return true;
    }

    private boolean checkTimestamp() {
        if (timestamp < 0) {
            return false;
        }

        /*判断时间戳是否在当前时间的1分钟内, 如果不是则抛异常*/
        LocalDateTime dateTimeGot = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(dateTimeGot, now);
        long minutes = duration.toMinutes();

        return minutes <= 1;
    }

    private boolean checkSign() {
        if (StrUtil.isBlank(sign)) {
            return false;
        }

        /*用md5根据以上字段重新生成sign, 然后和请求头中的sign比较, 不成功则抛异常*/
        return SignUtils.checkSign(sign, accessKey, secretKey, nonce, timestamp);
    }

}
