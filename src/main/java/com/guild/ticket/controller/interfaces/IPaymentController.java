package com.guild.ticket.controller.interfaces;

import com.guild.ticket.entity.Payment;
import com.guild.ticket.response.ResponseObject;

public interface IPaymentController {
    public ResponseObject getAllPayment(int page);

    public ResponseObject getPaymentByUsername(String username);

    public ResponseObject insertPayment(Payment payment);
}
