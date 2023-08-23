package com.guild.ticket.service.interfaces;

import com.guild.ticket.entity.Payment;
import com.guild.ticket.response.ResponseObject;

public interface IPaymentService {
    public ResponseObject getAllPayment(int page, int records);

    public ResponseObject getPaymentByUsername(String username);

    public ResponseObject removePayment(int id);

    public ResponseObject insertPayment(Payment payment);
}
