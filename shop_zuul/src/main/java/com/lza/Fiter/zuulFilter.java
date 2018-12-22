package com.lza.Fiter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class zuulFilter extends ZuulFilter {
    @Override
    //请求前或者后执行过滤器类型 pre之前 post之后
    public String filterType() {
        return "pre";
    }

    @Override
    //执行顺序，越小越先执行
    public int filterOrder() {
        return 0;
    }
  //当前过滤器是否开启，
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中执行的操作 return 任何值object类型都代表放行，如果不想放行，setsendzuulResponse（false） 表示不再继续执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
//        //得到request上下文
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        //得到request域
//        HttpServletRequest request = currentContext.getRequest();
//        //得到头信息
//        String header = request.getHeader("Authorization");
//        //判断是否有头信息
//
//            //把头信息继续向下传
//            currentContext.addZuulRequestHeader("Authorization", header);

        return null;


    }
}
