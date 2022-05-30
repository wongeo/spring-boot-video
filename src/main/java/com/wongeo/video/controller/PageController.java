package com.wongeo.video.controller;

import com.wongeo.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    final private VideoService videoService;

    @Autowired
    public PageController(VideoService service) {
        videoService = service;
    }

    //访问路径为：http://localhost:8080/file
    @RequestMapping("/file")
    public String file() {
        return "/file";
    }

    //访问路径为：http://localhost:8080
    @RequestMapping("/")
    public String index() {
        return "/index";
    }


    //访问路径为：http://localhost:8080/play
    @RequestMapping("/play")
    public String play() {
        return "/play";
    }
}
