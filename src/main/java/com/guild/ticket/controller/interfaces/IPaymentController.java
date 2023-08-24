package com.guild.ticket.controller.interfaces;

import com.guild.ticket.entity.Payment;
import com.guild.ticket.response.ResponseObject;

public interface IPaymentController {
    public ResponseObject getAllPayment(int page, int records);

    public ResponseObject getPaymentByUsername(String username);

    public ResponseObject removePayment(int id);

    public ResponseObject insertPayment(Payment payment);
}
