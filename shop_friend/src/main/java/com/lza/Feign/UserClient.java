package com.lza.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("shop-user")
public interface UserClient {
    @PutMapping(value = "/user/{userid}/{friendid}/{x}")
    public void updatefanscountandfollowcount(@PathVariable(value = "userid") String userid, @PathVariable(value = "friendid") String friendid, @PathVariable(value ="x" ) int x);
}
