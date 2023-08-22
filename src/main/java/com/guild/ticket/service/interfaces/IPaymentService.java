package com.guild.ticket.service.interfaces;

import com.guild.ticket.entity.Payment;
import com.guild.ticket.response.ResponseObject;

public interface IPaymentService {
    public ResponseObject getAllPayment(int page);

    public ResponseObject getPaymentByUsername(String username);

    public ResponseObject insertPayment(Payment payment);
}
