package com.sparta.binplay.service;

import com.sparta.binplay.repository.PaymentVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentVideoRepository paymentRepository;
//
//    @Scheduled(cron = "0 0 0 1 * ?") // 매달 1일 자정에 실행
//    public void archiveMonthlyPayments() {
//        LocalDate now = LocalDate.now();
//        YearMonth lastMonth = YearMonth.from(now.minusMonths(1));
//        LocalDateTime startOfLastMonth = lastMonth.atDay(1).atStartOfDay();
//        LocalDateTime endOfLastMonth = lastMonth.atEndOfMonth().atTime(23, 59, 59);
//
//        List<PaymentsVideo> paymentsList = paymentRepository.findAllByCreatedAtBetween(startOfLastMonth, endOfLastMonth);
//        for (PaymentsVideo payment : paymentsList) {
//            payment.updateArchived(true);
//            paymentRepository.save(payment);
//        }
//    }
}
