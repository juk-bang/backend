package com.jukbang.api.errorbot.filter;

import com.jukbang.api.errorbot.util.RequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.jukbang.api.errorbot.util.AgentUtils.getAgentDetail;
import static com.jukbang.api.errorbot.util.MDCUtil.*;

public class CollectRequestDataFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper requestWrapper = RequestWrapper.of(request);

        setJsonValueAndPutMDC(HEADER_MAP_MDC, requestWrapper.headerMap());
        setJsonValueAndPutMDC(PARAMETER_MAP_MDC, requestWrapper.parameterMap());
        setJsonValueAndPutMDC(BODY_MDC, requestWrapper.body());
        setJsonValueAndPutMDC(AGENT_DETAIL_MDC, getAgentDetail((HttpServletRequest) request));
        putMDC(REQUEST_URI_MDC, requestWrapper.getRequestUri());

        try {
            chain.doFilter(request, response);
        } finally {
            clear();
        }
    }
}