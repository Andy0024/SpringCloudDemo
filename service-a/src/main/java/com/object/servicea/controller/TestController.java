package com.object.servicea.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright: Copyright (c) 2019 Asiainfo
 *
 * @ClassName: com.object.servicea.controller.TestController
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: xianglt
 * @date: 2019/5/23 22:33
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2019/5/23      xianglt          v1.0.0               修改原因
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
