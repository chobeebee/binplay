package com.sparta.binplay;

import com.sparta.binplay.entity.*;
import com.sparta.binplay.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class GenerateTest {
    @Autowired
    private StreamRepository streamsRepository;

    @Autowired
    private AdViewRepository adViewsRepository;

    @Autowired
    private AdRepository adsRepository;

    @Autowired
    private VideoRepository videosRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoAdRepository videoAdRepository;

    public void generateData(int numRecords) {
        Random random = new Random();
        Users user = userRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Videos> videos = videosRepository.findAll();
        List<Ads> ads = adsRepository.findAll();
        List<VideoAd> videoAds = videoAdRepository.findAll();
        //List<> dd = new ArrayList<>(); //<- 여기

        for (Videos video : videos) {
            for (int i = 0; i < numRecords; i++) {
                int playTime = random.nextInt(1800) + 300;  // 300초에서 1800초 사이의 랜덤 시간
                Streams stream = new Streams();
                stream.setCreatedAt(LocalDateTime.now().minusDays(1));
                stream.setPlayTime(playTime);
                stream.setUser(user); // 예시로 user_id를 1로 설정, 실제로는 동적으로 설정 필요
                stream.setVideo(video);
                streamsRepository.save(stream);

                int numAds = playTime / 300;

                for (int j = 0; j < numAds; j++) {
                    VideoAd videoAd = videoAds.get(random.nextInt(videoAds.size()));
                    AdViews adView = new AdViews();
                    adView.setCreatedAt(LocalDate.now().minusDays(1));
                    adView.setVideoAd(videoAd);
                    adViewsRepository.save(adView);
                }
            }
        }
        //repository.saveAll();
    }

    @Test
    public void testGenerateData() { 
        generateData(2000);  // 10개의 기록을 생성-
    }
}
