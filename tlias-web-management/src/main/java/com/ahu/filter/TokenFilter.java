package com.ahu.filter;

import com.ahu.util.CurrentHolder;
import com.ahu.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //获取url或uri来判断是否包含过滤路径
        String url = req.getRequestURI();
        if(url.contains("/login")){
            log.info("登录请求，直接放行");
            chain.doFilter(req, res);
            return;
        }

        //获取请求头中的令牌
        String token = req.getHeader("token");
        if(!StringUtils.hasLength(token)){
            log.info("令牌为空，返回错误结果");
            res.setStatus(401);
            return;
        }

        //解析令牌
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = claims.get("id", Integer.class);
            String empUsername = claims.get("username",String.class);
            CurrentHolder.setCurrentId(empId);
            CurrentHolder.setCurrentUsername(empUsername);
            log.info("token解析成功，放行");
        }catch (Exception e){
            e.printStackTrace();
            log.info("令牌解析失败，返回错误结果");
            res.setStatus(401);
            return;
        }

        //放行
        log.info("令牌合法，放行");
        chain.doFilter(req, res);

        //清除当前线程的id
        CurrentHolder.remove();
    }
}
