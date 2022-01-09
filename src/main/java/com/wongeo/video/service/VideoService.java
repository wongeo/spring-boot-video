package com.wongeo.video.service;

import com.wongeo.video.model.Video;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class VideoService {

    public VideoService() throws UnknownHostException {
    }

    //插入
    public int insertUrl(String name, String path, String url) {
        System.out.print("开始插入=name==" + name + "\n");
        System.out.print("开始插入=path==" + path + "\n");
        System.out.print("开始插入=url==" + url + "\n");
        int res = 0;
        System.out.print("插入结果===" + res + "\n");
        return 0;
    }

    private InetAddress address = InetAddress.getLocalHost();

    private String getUrl(String name) {
        return "http://" + address.getHostAddress() + ":8080/files/" + name;
    }

    //查询
    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<>();
        File[] files = new File("/Users/feng/updateFiles/").listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String url = getUrl(file.getName());
            Video video = new Video(i, file.getName(), url);
            videos.add(video);
        }
        return videos;
    }
}