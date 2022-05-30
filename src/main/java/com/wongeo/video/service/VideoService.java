package com.wongeo.video.service;

import com.wongeo.video.model.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service
@Component
public class VideoService {

    @Value("${cbs.ip}")
    private String ip;

    public VideoService() throws UnknownHostException {

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

    private Map<String, Video> cache = new HashMap<>();

    public List<Video> getVideos(String name) {
        List<Video> videos = getVideos();
        List<Video> result = new ArrayList<>();
        for (Video video : videos) {
            if (video.getName().equals(name)) {
                result.add(video);
            }
        }
        return result;
    }

    //查询
    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<>();
        File[] files = new File(getFilesPath()).listFiles(pathname -> pathname.getName().contains(".mp4"));
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String url = getUrl(file.getName());
                Video video = new Video(i, file.getName(), url);
                String cover = getUrl("cover.png");
                if (new File(file.getAbsolutePath().replace(".mp4", ".jpg")).exists()) {
                    cover = getUrl(file.getName().replace(".mp4", ".jpg"));
                }
                video.setCover(cover);
                videos.add(video);
            }
        }
        return videos;
    }
}