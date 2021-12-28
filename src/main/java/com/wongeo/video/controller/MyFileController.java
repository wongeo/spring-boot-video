package com.wongeo.video.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wongeo.video.model.Video;
import com.wongeo.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class MyFileController {

    final private VideoService videoService;

    private String url;

    @Autowired
    public MyFileController(VideoService service) {
        videoService = service;
    }

    //访问路径为：http://localhost:8080/file
    @RequestMapping("/file")
    public String file(){
        return "/file";
    }

    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String uploadFile(@RequestParam("fileName") MultipartFile file) {
        System.out.print("上传文件===" + "\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return "上传文件不可为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //加个时间戳，尽量避免文件名称重复
        String path = "/Users/feng/updateFiles/" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径" + path + "\n");
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
            return "文件已经存在";
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径" + path + "\n");
            url = "http://localhost:8080/files/" + fileName;//本地运行项目
            int res = videoService.insertUrl(fileName, path, url);
            System.out.print("插入结果" + res + "\n");
            System.out.print("保存的完整url====" + url + "\n");

        } catch (IOException e) {
            return "上传失败";
        }
        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("msg", "success");
        object.put("url", url);
        return object.toString();
    }

    //查询
    @RequestMapping("/list")
    @ResponseBody
    public String list(Model model) {
        List<Video> videos = videoService.getVideos();
        model.addAttribute("Videos", videos);
        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("data", JSON.toJSON(videos));
        return object.toString();
    }
}