package com.ite.pickon.domain.sms.event;

import com.ite.pickon.domain.sms.service.SmsService;
import com.ite.pickon.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.ite.pickon.exception.ErrorCode.FAIL_SEND_SMS;

@Component
@RequiredArgsConstructor
public class SmsSendEventListener {
    private final SmsService smsService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCompletedEvent(SmsSendEvent event) {
        try {
            // SMS 발송
            smsService.sendSms(event.getUserPhoneNumber(), event.getMessage());
        } catch (Exception e) {
            throw new CustomException(FAIL_SEND_SMS);
        }
    }
}
