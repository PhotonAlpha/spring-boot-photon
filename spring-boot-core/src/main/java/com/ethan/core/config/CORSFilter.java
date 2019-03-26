/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/03/11
 */
package com.ethan.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CORSFilter implements Filter {
    private final static List<String> allowedOrigins = Arrays.asList("http://localhost:4200","http://47.103.64.224:4200");
    private final static String WEBSOCKET_REQ = "/socket/info";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
// Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            // Access-Control-Allow-Origin
            String origin = request.getHeader("Origin");
            // if(request.getRequestURI().contains(WEBSOCKET_REQ)) {
                log.info("---------Web Socket CORS Filter{}", origin);
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Vary", "Origin");

                // Access-Control-Max-Age
                response.setHeader("Access-Control-Max-Age", "3600");

                // Access-Control-Allow-Credentials
                response.setHeader("Access-Control-Allow-Credentials", "true");

                // Access-Control-Allow-Methods
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

                // Access-Control-Allow-Headers
                response.setHeader("Access-Control-Allow-Headers",
                        "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
            // }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
