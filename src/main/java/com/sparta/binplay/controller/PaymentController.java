package com.sparta.binplay.controller;

import com.sparta.binplay.dto.DailyTotalAmountDto;
import com.sparta.binplay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    private final PaymentService paymentService;
   /* private final PaymentVideoRepository paymentVideoRepository;
    private final PaymentAdRepository paymentAdRepository;

    @GetMapping("/video")
    public List<PaymentVideo> getDailyVideoPayments(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return paymentVideoRepository.findAllByCreatedAt(date);
    }

    @GetMapping("/ad")
    public List<PaymentAd> getDailyAdPayments(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return paymentAdRepository.findAllByCreatedAt(date.atStartOfDay().toLocalDate());
    }

    // 모든 비디오의 정산 데이터를 조회하는 API
    @GetMapping("/all-videos")
    public ResponseEntity<List<PaymentVideoResponseDto>> getAllVideoPayments(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<PaymentVideoResponseDto> payments = paymentService.getAllVideoPayments(date);
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }

    // 특정 날짜의 비디오 정산 데이터를 계산하는 API
    @PostMapping("/calculate-video")
    public ResponseEntity<Void> calculateVideoPayment(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        paymentService.calculateVideoPmt(date);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 특정 날짜의 광고 정산 데이터를 계산하는 API
    @PostMapping("/calculate-ad")
    public ResponseEntity<Void> calculateAdPayment(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        paymentService.calculateAdPmt(date);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/

    // 특정 날짜의 총 정산 금액을 조회하는 API
   /* @GetMapping("/total-payment")
    public ResponseEntity<DailyTotalAmountDto> getDailyTotalPayment(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DailyTotalAmountDto totalPayment = paymentService.getDailyTotalPayment(date);
        return ResponseEntity.status(HttpStatus.OK).body(totalPayment);
    }
*/
    @GetMapping("/total-payment/day")
    public ResponseEntity<DailyTotalAmountDto> getDailyTotalPayment(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DailyTotalAmountDto totalPayment = paymentService.getDailyTotalPayment(date);
        return ResponseEntity.status(HttpStatus.OK).body(totalPayment);
    }

    @GetMapping("/total-payment/week")
    public ResponseEntity<DailyTotalAmountDto> getWeeklyTotalPayment(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DailyTotalAmountDto totalPayment = paymentService.getTotalPaymentForWeek(date);
        return ResponseEntity.status(HttpStatus.OK).body(totalPayment);
    }

    @GetMapping("/total-payment/month")
    public ResponseEntity<DailyTotalAmountDto> getMonthlyTotalPayment(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DailyTotalAmountDto totalPayment = paymentService.getTotalPaymentForMonth(date);
        return ResponseEntity.status(HttpStatus.OK).body(totalPayment);
    }
}
