package com.sparta.binplay.service;

import com.sparta.binplay.dto.DailyTotalAmountDto;
import com.sparta.binplay.repository.PaymentAdRepository;
import com.sparta.binplay.repository.PaymentVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentVideoRepository paymentVideoRepository;
    private final PaymentAdRepository paymentAdRepository;
    /*private final VideoRepository videoRepository;
    private final VideoAdRepository videoAdRepository;
    private final StatisticVideoRepository statisticVideoRepository;
    private final StatisticAdRepository statisticAdRepository;

    // 1일 비디오 정산
    public void calculateVideoPmt(LocalDate date) {

        List<StatisticVideo> stats = statisticVideoRepository.findByCreatedAt(date);

        for (StatisticVideo stat : stats) {
            double totalAmount = Math.floor(calculateVideoAmount(stat.getDailyViewCount()));
            PaymentVideo paymentVideo = paymentVideoRepository.findByVideoAndCreatedAt(stat.getVideo(), date)
                    .orElseGet(() -> PaymentVideo.builder()
                            .video(stat.getVideo())
                            .createdAt(date)
                            .totalAmount(0.0)
                            .build());

            paymentVideo.updateTotalAmount(totalAmount);
            paymentVideoRepository.save(paymentVideo);
            
            // 비디오 총 조회수 업데이트
            Videos video = stat.getVideo();
            video.updateViewCount(video.getViewCount() + stat.getDailyViewCount());
            videoRepository.save(video);
        }
    }

    // 1일 광고 정산
    public void calculateAdPmt(LocalDate date) {

        List<StatisticAd> stats = statisticAdRepository.findByCreatedAt(date);

        for (StatisticAd stat : stats) {
            double totalAmount = Math.floor(calculateAdAmount(stat.getDailyViewCount()));
            PaymentAd paymentAd = paymentAdRepository.findByVideoAdAndCreatedAt(stat.getVideoAd(), date)
                    .orElseGet(() -> PaymentAd.builder()
                            .videoAd(stat.getVideoAd())
                            .createdAt(date)
                            .totalAmount(0.0)
                            .build());

            paymentAd.updateTotalAmount(totalAmount);
            paymentAdRepository.save(paymentAd);

            // 광고 총 조회수 업데이트
            VideoAd videoAd = stat.getVideoAd();
            videoAd.updateViewCount(videoAd.getViewCount() + stat.getDailyViewCount());
            videoAdRepository.save(videoAd);
        }
    }

    // 모든 비디오 정산 데이터 조회
    public List<PaymentVideoResponseDto> getAllVideoPayments(LocalDate date) {
        List<PaymentVideo> payments = paymentVideoRepository.findAllByCreatedAt(date);
        return payments.stream()
                .map(PaymentVideoResponseDto::from)
                .collect(Collectors.toList());
    }

    // 비디오 정산 데이터 조회
    public List<PaymentVideoResponseDto> getDailyVideoPayments(LocalDate date) {
        List<PaymentVideo> payments = paymentVideoRepository.findAllByCreatedAt(date);
        return payments.stream()
                .map(PaymentVideoResponseDto::from)
                .collect(Collectors.toList());
    }

    // 광고 정산 데이터 조회
    public List<PaymentAdResponseDto> getDailyAdPayments(LocalDate date) {
        List<PaymentAd> payments = paymentAdRepository.findAllByCreatedAt(date);
        return payments.stream()
                .map(PaymentAdResponseDto::from)
                .collect(Collectors.toList());
    }*/

    /*// 총 금액, 광고 정산, 영상 정산
    public DailyTotalAmountDto getDailyTotalPayment(LocalDate date) {
        double totalVideoAmount = paymentVideoRepository.findAllByCreatedAt(date).stream()
                .mapToDouble(payment -> Math.floor(payment.getTotalAmount()))
                .sum();

        double totalAdAmount = paymentAdRepository.findAllByCreatedAt(date).stream()
                .mapToDouble(payment -> Math.floor(payment.getTotalAmount()))
                .sum();

        double totalAmount = totalVideoAmount + totalAdAmount;

        return DailyTotalAmountDto.builder()
                .totalVideoAmount(totalVideoAmount)
                .totalAdAmount(totalAdAmount)
                .totalAmount(totalAmount)
                .build();
    }*/

    /*// 비디오 단가
    private double calculateVideoAmount(long viewCount) {
        if (viewCount < 100000) return viewCount * 1;
        else if (viewCount < 500000) return viewCount * 1.1;
        else if (viewCount < 1000000) return viewCount * 1.3;
        else return viewCount * 1.5;
    }

    // 광고 단가
    private double calculateAdAmount(long viewCount) {
        if (viewCount < 100000) return viewCount * 10;
        else if (viewCount < 500000) return viewCount * 12;
        else if (viewCount < 1000000) return viewCount * 15;
        else return viewCount * 20;
    }*/


    public DailyTotalAmountDto getDailyTotalPayment(LocalDate date) {
        return calculateTotalPayment(date, date);
    }

    public DailyTotalAmountDto getTotalPaymentForWeek(LocalDate date) {
        LocalDate startOfWeek = date.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = date.with(java.time.DayOfWeek.SUNDAY);
        return calculateTotalPayment(startOfWeek, endOfWeek);
    }

    public DailyTotalAmountDto getTotalPaymentForMonth(LocalDate date) {
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return calculateTotalPayment(startOfMonth, endOfMonth);
    }

    private DailyTotalAmountDto calculateTotalPayment(LocalDate startDate, LocalDate endDate) {
        Double totalVideoAmount = paymentVideoRepository.findTotalAmountByCreatedAtBetween(startDate, endDate);
        Double totalAdAmount = paymentAdRepository.findTotalAmountByCreatedAtBetween(startDate, endDate);

        if (totalVideoAmount == null) totalVideoAmount = 0.0;
        if (totalAdAmount == null) totalAdAmount = 0.0;

        double totalVideoAmountRounded = Math.floor(totalVideoAmount);
        double totalAdAmountRounded = Math.floor(totalAdAmount);
        double totalAmountRounded = Math.floor(totalVideoAmount + totalAdAmount);

        return DailyTotalAmountDto.builder()
                .totalVideoAmount(totalVideoAmountRounded)
                .totalAdAmount(totalAdAmountRounded)
                .totalAmount(totalAmountRounded)
                .build();
    }
}
