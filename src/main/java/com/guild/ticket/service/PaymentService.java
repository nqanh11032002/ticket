package com.guild.ticket.service;

import com.guild.ticket.entity.Payment;
import com.guild.ticket.repository.IPaymentRepository;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public ResponseObject getAllPayment(int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id"); // Example sorting by 'id'
        PageRequest pageRequest = PageRequest.of(page, 2, sort);

        List<Payment> payments = paymentRepository.findAll(pageRequest).getContent();

        if(payments.size() == 0)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("List of payment is empty")
                    .data(payments).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("List of payment has been found")
                .data(payments).build();
    }

    @Override
    public ResponseObject getPaymentByUsername(String username) {
        var payment = paymentRepository.getPaymentByUsername(username);

        if(payment.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message("Not found payment by Username: " + username)
                    .data(payment).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Payment found with ID: " + username)
                .data(payment).build();
    }

    @Override
    public ResponseObject insertPayment(Payment payment) {
        paymentRepository.save(payment);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message("Insert new payment successfully")
                .data(payment).build();
    }
}
