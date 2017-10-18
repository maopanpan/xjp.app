package com.xjp.app.controller.example;

import com.xjp.app.common.interceptor.pageinterceptor.Page;
import com.xjp.app.model.example.SysUser;
import com.xjp.app.service.example.UserService;
import com.xjp.app.vo.app.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: maopanpan
 * @Description:
 * @Date: 2017/10/12.
 **/
@RestController
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUserByID")
    public Object findUserById(@RequestParam(value = "id", required = true) long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        SysUser sysUser = userService.findById(params);
        return sysUser;
    }

    @GetMapping("/findPage")
    public Object findPage() {
        Page<SysUser> page = userService.findPage(new Page<SysUser>(), new SysUser());
        return page;
    }

    @GetMapping("/register")
    public Object register(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "name", required = true) String name,
                           @RequestParam(value = "idCard", required = true) String idCard, @RequestParam(value = "age", required = true) int age) throws Exception {
        Map<String, Object> params = new HashMap<>();
       // params.put("id", id);
        params.put("name", name);
        params.put("idCard", idCard);
        params.put("age", age);
        userService.saveSysUser(params);

        ResponseObject responseObject = new ResponseObject(params);
        return responseObject;
    }
}
