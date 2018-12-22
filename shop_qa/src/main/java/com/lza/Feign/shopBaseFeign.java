package com.lza.Feign;

import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "shop-base",fallback =shopBaseFeign.shopBaseFeignFallbcak.class )
public interface shopBaseFeign {
    @GetMapping(value = "/label/{labelId}")
    public Result findById(@PathVariable("labelId") String id);

    @Component
    static  class shopBaseFeignFallbcak implements shopBaseFeign{

        @Override
        public Result findById(String id) {
            return new Result(false, StatusCode.ERROR, "服务降级触发了！");
        }
    }
}
