package com.simple.controller;

import com.simple.service.HeadLineService;
import org.simpleframework.core.inject.annotation.Controller;
import org.simpleframework.core.inject.annotation.Autowired;

/**
 * 头条信息控制器
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/7 20:16
 */
@Controller
public class HeadLineController {

    @Autowired
    private HeadLineService headLineService;


}
