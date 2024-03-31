package com.pan.admin.handler;

import cn.dev33.satoken.spring.SpringMVCUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.pan.common.util.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * Mybatis Plus允许在插入或者更新的时候
 * 自定义设定值
 * @author valarchie
 */
@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    private static final Long DEFAULT_USER_ID = 0L;
    private static final String CREATOR_ID_FIELD = "creatorId";
    private static final String UPDATER_ID_FIELD = "updaterId";


    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = getUserId();
        if (metaObject.hasSetter(CREATOR_ID_FIELD)) {
            if (metaObject.getValue(CREATOR_ID_FIELD) == null) {
                this.strictInsertFill(metaObject, CREATOR_ID_FIELD, Long.class, userId);
            }
        }

        if (metaObject.hasSetter(UPDATER_ID_FIELD)) {
            if (metaObject.getValue(UPDATER_ID_FIELD) == null) {
                this.strictInsertFill(metaObject, UPDATER_ID_FIELD, Long.class, userId);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = getUserId();

        if (metaObject.hasSetter(UPDATER_ID_FIELD)) {
            if (metaObject.getValue(UPDATER_ID_FIELD) == null) {
                this.strictInsertFill(metaObject, UPDATER_ID_FIELD, Long.class, userId);
            }
        }
    }

    private Long getUserId() {
        if (SpringMVCUtil.isWeb() && StpUtil.isLogin()) {
            return AuthUtils.getLoginUserId();
        }

        return DEFAULT_USER_ID;
    }
}
