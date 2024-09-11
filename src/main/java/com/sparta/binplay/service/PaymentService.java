package com.sparta.binplay.service;

import com.sparta.binplay.dto.DailyTotalAmountDto;
import com.sparta.binplay.dto.MonthlyTotalAmountDto;
import com.sparta.binplay.dto.VideoAmountDto;
import com.sparta.binplay.dto.WeeklyTotalAmountDto;
import com.sparta.binplay.entity.payment.PaymentAd;
import com.sparta.binplay.entity.payment.PaymentVideo;
import com.sparta.binplay.repository.PaymentAdRepository;
import com.sparta.binplay.repository.PaymentVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentVideoRepository paymentVideoRepository;
    private final PaymentAdRepository paymentAdRepository;

    public DailyTotalAmountDto getDailyPaymentSummary(LocalDate date) {
        List<PaymentVideo> videoPayments = paymentVideoRepository.findByCreatedAt(date);
        List<PaymentAd> adPayments = paymentAdRepository.findByCreatedAt(date);

        Map<Long, VideoAmountDto> breakdownMap = new HashMap<>();

        BigDecimal totalVideoAmount = BigDecimal.ZERO;
        BigDecimal totalAdAmount = BigDecimal.ZERO;

        for (PaymentVideo payment : videoPayments) {
            BigDecimal amount = BigDecimal.valueOf(payment.getTotalAmount());
            Long videoId = payment.getVideo().getVideoId();
            breakdownMap.computeIfAbsent(videoId, k -> new VideoAmountDto(videoId.toString(), BigDecimal.ZERO, BigDecimal.ZERO))
                    .addVideoAmount(amount);
            totalVideoAmount = totalVideoAmount.add(amount);
        }

        for (PaymentAd payment : adPayments) {
            BigDecimal amount = BigDecimal.valueOf(payment.getTotalAmount());
            Long videoId = payment.getVideoAd().getVideo().getVideoId();
            breakdownMap.computeIfAbsent(videoId, k -> new VideoAmountDto(videoId.toString(), BigDecimal.ZERO, BigDecimal.ZERO))
                    .addAdAmount(amount);
            totalAdAmount = totalAdAmount.add(amount);
        }

        List<VideoAmountDto> breakdowns = new ArrayList<>(breakdownMap.values());

        return new DailyTotalAmountDto(totalVideoAmount, totalAdAmount, breakdowns);
    }

    public WeeklyTotalAmountDto getWeeklyPaymentSummary() {
        LocalDate today = LocalDate.now();

        // 오늘 날짜가 포함된 주의 월요일을 구함
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        // 오늘 날짜가 포함된 주의 일요일을 구함
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        BigDecimal totalVideoAmountForWeek = BigDecimal.ZERO;
        BigDecimal totalAdAmountForWeek = BigDecimal.ZERO;
        Map<String, VideoAmountDto> videoSummaryMap = new HashMap<>();

        for (LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)) {
            DailyTotalAmountDto dailySummary = getDailyPaymentSummary(date);

            totalVideoAmountForWeek = totalVideoAmountForWeek.add(dailySummary.getTotalVideoAmount());
            totalAdAmountForWeek = totalAdAmountForWeek.add(dailySummary.getTotalAdAmount());

            for (VideoAmountDto videoBreakdown : dailySummary.getVideoBreakdowns()) {
                String videoId = videoBreakdown.getVideoId();
                videoSummaryMap.computeIfAbsent(videoId, k -> new VideoAmountDto(videoId, BigDecimal.ZERO, BigDecimal.ZERO))
                        .addVideoAmount(videoBreakdown.getVideoAmount())
                        .addAdAmount(videoBreakdown.getAdAmount());
            }
        }

        // 비디오별 합산 결과 리스트
        List<VideoAmountDto> videoSummaries = new ArrayList<>(videoSummaryMap.values());

        return new WeeklyTotalAmountDto(totalVideoAmountForWeek, totalAdAmountForWeek, videoSummaries);
    }

    public MonthlyTotalAmountDto getMonthlyPaymentSummary() {
        LocalDate today = LocalDate.now();

        // 이번 달의 첫 번째 날
        LocalDate startOfMonth = today.withDayOfMonth(1);
        // 이번 달의 마지막 날
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        BigDecimal totalVideoAmountForMonth = BigDecimal.ZERO;
        BigDecimal totalAdAmountForMonth = BigDecimal.ZERO;
        Map<String, VideoAmountDto> videoSummaryMap = new HashMap<>();

        // 월간 데이터를 처리
        for (LocalDate date = startOfMonth; !date.isAfter(endOfMonth); date = date.plusDays(1)) {
            DailyTotalAmountDto dailySummary = getDailyPaymentSummary(date);

            totalVideoAmountForMonth = totalVideoAmountForMonth.add(dailySummary.getTotalVideoAmount());
            totalAdAmountForMonth = totalAdAmountForMonth.add(dailySummary.getTotalAdAmount());

            for (VideoAmountDto videoBreakdown : dailySummary.getVideoBreakdowns()) {
                String videoId = videoBreakdown.getVideoId();
                videoSummaryMap.computeIfAbsent(videoId, k -> new VideoAmountDto(videoId, BigDecimal.ZERO, BigDecimal.ZERO))
                        .addVideoAmount(videoBreakdown.getVideoAmount())
                        .addAdAmount(videoBreakdown.getAdAmount());
            }
        }

        // 비디오별 합산 결과 리스트
        List<VideoAmountDto> videoSummaries = new ArrayList<>(videoSummaryMap.values());

        return new MonthlyTotalAmountDto(totalVideoAmountForMonth, totalAdAmountForMonth, videoSummaries);
    }
}
