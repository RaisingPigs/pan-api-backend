package com.pan.admin.event;

import com.pan.model.entity.SysOperationLog;
import com.pan.model.event.BaseEvent;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-31 17:23
 **/
public class OperationLogEvent<S> extends BaseEvent<S, SysOperationLog> {
    public OperationLogEvent(S source, SysOperationLog data) {
        super(source, data);
    }
}
