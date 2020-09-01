package com.jukbang.api.errorbot;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.jukbang.api.errorbot.config.LogConfig;
import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jukbang.api.errorbot.util.JsonUtils.toPrettyJson;
import static com.jukbang.api.errorbot.util.MDCUtil.*;
import static org.apache.commons.text.StringEscapeUtils.unescapeJava;

@RequiredArgsConstructor
public class ErrorReportAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private final LogConfig logConfig;
    private final ErrorLogsRepository errorLogsRepository;

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (eventObject.getLevel().isGreaterOrEqual(logConfig.getLevel())) {
            ErrorLogs errorLog = new ErrorLogs(
                    eventObject,
                    logConfig.getSystemName(),
                    getFromMDC(REQUEST_URI_MDC),
                    getFromMDC(PARAMETER_MAP_MDC),
                    getFromMDC(HEADER_MAP_MDC),
                    getFromMDC(BODY_MDC),
                    getFromMDC(AGENT_DETAIL_MDC)
            );
            if (logConfig.getDatabase().isEnabled())
                errorLogsRepository.save(errorLog);
            if (logConfig.getSlack().isEnabled())
                sendSlackMessage(errorLog);
        }
    }

    private void sendSlackMessage(ErrorLogs errorLog) {
        errorLog.markAsAlert();
        SlackApi slackApi = new SlackApi(logConfig.getSlack().getWebHookUrl());

        SlackMessage slackMessage = new SlackMessage("");
        slackMessage.setChannel("#" + logConfig.getSlack().getChannel());
        slackMessage.setUsername(logConfig.getSlack().getUserName());
        slackMessage.setIcon(":exclamation:");

        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback("Api 서버 에러발생!! 확인요망");
        slackAttachment.setColor("danger");
        if (logConfig.getDatabase().isEnabled())
            slackAttachment.setTitle(errorLog.getErrorInfo().getMessage() + "[" + errorLog.getId() + "] ");
        else
            slackAttachment.setTitle(errorLog.getErrorInfo().getMessage());
        slackAttachment.setText(errorLog.getErrorInfo().getTrace());

        List<SlackField> fields = new ArrayList<>();

        fields.add(generateSlackField(
                "Request URL",
                errorLog.getRequestInfo().getPath(),
                true));

        fields.add(generateSlackField(
                "Request Parameter",
                toPrettyJson(errorLog.getRequestInfo().getParameterMap()),
                true));

        fields.add(generateSlackField(
                "Request Header",
                toPrettyJson(errorLog.getRequestInfo().getHeaderMap()),
                false));

        fields.add(generateSlackField(
                "Request Body",
                unescapeJava(toPrettyJson(errorLog.getRequestInfo().getBody())),
                false));

        fields.add(generateSlackField(
                "User Info",
                errorLog.getUserInfo(),
                true));

        fields.add(generateSlackField(
                "User Agent",
                toPrettyJson(errorLog.getRequestInfo().getAgentDetail()),
                true));

        fields.add(generateSlackField(
                "Time",
                errorLog.getErrorDatetime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                true));

        fields.add(generateSlackField(
                "Server Name",
                errorLog.getSystemInfo().getServerName(),
                true));

        fields.add(generateSlackField(
                "Server System OS",
                errorLog.getSystemInfo().getSystem(),
                true));

        fields.add(generateSlackField(
                "Server Host Name",
                errorLog.getSystemInfo().getHostName(),
                true));

        slackAttachment.setFields(fields);
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));
        slackApi.call(slackMessage);
    }

    private SlackField generateSlackField(String title, String value, Boolean shorten) {
        SlackField slackField = new SlackField();
        slackField.setTitle(title);
        slackField.setValue(value);
        slackField.setShorten(shorten);
        return slackField;
    }
}