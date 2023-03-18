package com.pyo.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//  이제 아이디에 맞는 토큰을 만들어준다. id ,pw 정상적으로 들어와서 로그인이 되면 그때 토큰을 만들어주고 할당.
        // 그 후 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증하면 된다.

        if (req.getMethod().equals("POST")) {
            System.out.println("포스트 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("필터1");

            if (headerAuth.equals("cos")) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("no access");
            }

        }


    }

}
