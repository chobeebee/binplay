package com.sparta.binplay.service;

import com.sparta.binplay.dto.DailyTotalAmountDto;
import com.sparta.binplay.dto.response.PaymentAdResponseDto;
import com.sparta.binplay.dto.response.PaymentVideoResponseDto;
import com.sparta.binplay.entity.payment.PaymentAd;
import com.sparta.binplay.entity.payment.PaymentVideo;
import com.sparta.binplay.entity.statistic.StatisticAd;
import com.sparta.binplay.entity.statistic.StatisticVideo;
import com.sparta.binplay.repository.PaymentAdRepository;
import com.sparta.binplay.repository.PaymentVideoRepository;
import com.sparta.binplay.repository.StatisticAdRepository;
import com.sparta.binplay.repository.StatisticVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentVideoRepository paymentVideoRepository;
    private final PaymentAdRepository paymentAdRepository;
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
    }

    // 총 금액, 광고 정산, 영상 정산
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
    }

    // 비디오 단가
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
    }
}
