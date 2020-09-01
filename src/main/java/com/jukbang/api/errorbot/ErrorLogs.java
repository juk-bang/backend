package com.jukbang.api.errorbot;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.jukbang.api.errorbot.embedded.ErrorInfo;
import com.jukbang.api.errorbot.embedded.RequestInfo;
import com.jukbang.api.errorbot.embedded.SystemInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ErrorLogs {
    @Id
    @Column(name = "ID", precision = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "USER_INFO", columnDefinition = "TEXT")
    private String userInfo;

    @Embedded
    private SystemInfo systemInfo;

    @Embedded
    private ErrorInfo errorInfo;

    @Embedded
    private RequestInfo requestInfo;

    @Column(name = "ALERT_YN", length = 1)
    private Boolean alert = false;

    @Column(name = "ERROR_DATETIME")
    private final LocalDateTime errorDatetime = LocalDateTime.now();

    public ErrorLogs(ILoggingEvent eventObject,String systemName, String uri, String parameter, String header, String body, String agent) {
        this.userInfo = SecurityContextHolder.getContext().getAuthentication().getName();
        this.systemInfo = new SystemInfo(systemName);
        this.errorInfo = new ErrorInfo(eventObject);
        this.requestInfo = new RequestInfo(uri, parameter, header, body, agent);
    }

    public void markAsAlert() {
        this.alert = true;
    }
}
