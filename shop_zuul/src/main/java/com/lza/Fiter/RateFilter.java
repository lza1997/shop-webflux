package com.lza.Fiter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component
public class RateFilter  extends ZuulFilter {

    //使用令牌桶算法完成限流
    private  static  final RateLimiter ratelimiter =RateLimiter.create(100);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -4;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果没有拿到令牌，就不能访问，返回
    if (!ratelimiter.tryAcquire()){
        try {
            throw  new RateException();
        } catch (RateException e) {
            e.printStackTrace();
        }
    }

        return null;
    }

    public class RateException extends Exception {
    }
}
