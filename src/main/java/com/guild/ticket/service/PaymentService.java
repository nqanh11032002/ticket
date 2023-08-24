package com.guild.ticket.service;

import com.guild.ticket.entity.Payment;
import com.guild.ticket.mapper.ITicketMapper;
import com.guild.ticket.repository.IPaymentRepository;
import com.guild.ticket.response.ResponseMessage;
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
    public ResponseObject getAllPayment(int page, int records) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, records, sort);

        List<Payment> payments = paymentRepository.findAll(pageRequest).getContent();

        if(payments.size() == 0)
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.getAllPaymentEmpty)
                    .data(payments).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.getAllPayment)
                .data(payments).build();
    }

    @Override
    public ResponseObject getPaymentByUsername(String username) {
        var payment = paymentRepository.getPaymentByUsername(username);

        if(payment.isEmpty())
        {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.getPaymentNotFound)
                    .data(payment).build();
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.getPayment)
                .data(payment).build();
    }

    @Override
    public ResponseObject removePayment(int id) {
        var paymentFound = paymentRepository.findById(id);

        if (paymentFound.isEmpty()) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.name())
                    .message(ResponseMessage.removePaymentFail)
                    .data(null).build();
        }

        paymentRepository.deleteById(id);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.removePayment)
                .data(paymentFound).build();
    }

    @Override
    public ResponseObject insertPayment(Payment payment) {
        paymentRepository.save(payment);

        return ResponseObject.builder()
                .status(HttpStatus.OK.name())
                .message(ResponseMessage.insertPayment)
                .data(payment).build();
    }
}
