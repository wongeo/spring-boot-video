package com.wongeo.video.service;

import com.wongeo.video.model.Video;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${cbs.ip}")
    private String ip;

    public VideoService() throws UnknownHostException {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            ip = address.getHostAddress();
        }
    }

    //插入
    public int insertUrl(String name, String path, String url) {
        return 0;
    }

    private InetAddress address = InetAddress.getLocalHost();

    private String getUrl(String name) {
        return "http://" + ip + ":8080/files/" + name;
    }

    @Value("F:/Downloads/a/")
    private String filesPath;

    private String getFilesPath() {
        String filesPath;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            filesPath = "F:/Downloads/a/";
        } else {
            filesPath = "/Users/feng/updateFiles/";
        }
        return filesPath;
    }

    //查询
    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<>();
        File[] files = new File(getFilesPath()).listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String url = getUrl(file.getName());
                Video video = new Video(i, file.getName(), url);
                videos.add(video);
            }
        }
        return videos;
    }
}