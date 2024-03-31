package com.pan.admin.event;

import com.pan.model.entity.SysLoginLog;
import com.pan.model.event.BaseEvent;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-28 15:37
 **/
public class LoginLogEvent<S> extends BaseEvent<S, SysLoginLog> {
    public LoginLogEvent(S source, SysLoginLog data) {
        super(source, data);
    }
}
