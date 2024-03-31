package com.pan.admin.listener;

import com.pan.admin.config.ThreadPoolConfig;
import com.pan.admin.event.OperationLogEvent;
import com.pan.admin.service.SysOperationLogService;
import com.pan.model.entity.SysOperationLog;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-31 17:22
 **/
@Component
@RequiredArgsConstructor
public class OperationLogListener {
    private final SysOperationLogService sysOperationLogService;

    @Async(ThreadPoolConfig.EVENT_THREAD_POOL)
    @EventListener
    public void listenOperationLog(OperationLogEvent<?> operationLogEvent) {
        SysOperationLog sysOperationLog = operationLogEvent.getData();
        sysOperationLogService.save(sysOperationLog);
    }
}
