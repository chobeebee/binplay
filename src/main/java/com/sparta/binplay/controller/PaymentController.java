package com.sparta.binplay.controller;

import com.sparta.binplay.dto.DailyTotalAmountDto;
import com.sparta.binplay.dto.MonthlyTotalAmountDto;
import com.sparta.binplay.dto.WeeklyTotalAmountDto;
import com.sparta.binplay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/day")
    public ResponseEntity<?> getDayPayment(@RequestParam(value = "date", required = false) String today) {
        LocalDate date = (today != null) ? LocalDate.parse(today) : LocalDate.now().minusDays(1);

        DailyTotalAmountDto summary = paymentService.getDailyPaymentSummary(date);

        if (summary.getTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"해당 날짜의 데이터가 없습니다.\"}");
        }

        return ResponseEntity.ok(summary);
    }

    @GetMapping("/week")
    public ResponseEntity<?> getWeeklyPayment() {

        WeeklyTotalAmountDto summary = paymentService.getWeeklyPaymentSummary();

        if (summary.getTotalAmountForWeek().compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"해당 날짜의 데이터가 없습니다.\"}");
        }

        return ResponseEntity.ok(summary);
    }

    @GetMapping("/month")
    public ResponseEntity<?> getMonthlyPayment() {

        MonthlyTotalAmountDto summary = paymentService.getMonthlyPaymentSummary();

        if (summary.getTotalAmountForMonth().compareTo(BigDecimal.ZERO) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"해당 날짜의 데이터가 없습니다.\"}");
        }

        return ResponseEntity.ok(summary);
    }
}
