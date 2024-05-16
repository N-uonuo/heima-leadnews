package com.heima.app.gateway.filter;

import com.heima.app.gateway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //exchange对象是请求和响应的上下文对象，他是一个接口，实现类是DefaultServerWebExchange
        //chain对象是过滤器链对象，用于执行过滤器链

        //获取request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //判断是否登录
        if(request.getURI().getPath().contains("/login")){
            //放行
            return chain.filter(exchange);
        }

        //获取token
        String token = request.getHeaders().getFirst("token");

        //判断token是否存在
        if(StringUtils.isBlank(token)){
            //设置响应状态码为401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //返回
            return response.setComplete();
        }

        //判断token是否有效
        try{
            //解析token
            Claims claims = AppJwtUtil.getClaimsBody(token);
            //是否过期
            int result = AppJwtUtil.verifyToken(claims);

            if (result == 1 || result == 2) {
                //设置响应状态码为401
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //返回
                return response.setComplete();
            }

        }catch (Exception e){
            //设置响应状态码为401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //返回
            return response.setComplete();
        }

        //放行
        return chain.filter(exchange);


    }

    // 过滤器的执行顺序，返回值越小，执行优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
