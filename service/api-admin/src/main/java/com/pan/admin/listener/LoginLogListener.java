package com.pan.admin.listener;

import com.pan.admin.config.ThreadPoolConfig;
import com.pan.admin.event.LoginLogEvent;
import com.pan.admin.service.SysLoginLogService;
import com.pan.model.entity.SysLoginLog;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-03-28 15:31
 **/
@Component
@RequiredArgsConstructor
public class LoginLogListener {
    private final SysLoginLogService sysLoginLogService;

    @Async(ThreadPoolConfig.EVENT_THREAD_POOL)
    @EventListener
    public void listenLoginLog(LoginLogEvent<?> event) {
        SysLoginLog loginLog = event.getData();
        sysLoginLogService.save(loginLog);
    }
}
