package com.guild.ticket.controller;

import com.guild.ticket.controller.interfaces.IPaymentController;
import com.guild.ticket.entity.Payment;
import com.guild.ticket.response.ResponseObject;
import com.guild.ticket.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController implements IPaymentController {
    @Autowired
    private IPaymentService paymentService;

    @Override
    @GetMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject getAllPayment(@RequestParam("page") int page, int records) {
        return paymentService.getAllPayment(page, records);
    }

    @Override
    @GetMapping("/find")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject getPaymentByUsername(@RequestParam("username") String username) {
        return paymentService.getPaymentByUsername(username);
    }

    @Override
    @DeleteMapping("")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject removePayment(@RequestParam("id") int id) {
        return paymentService.removePayment(id);
    }

    @Override
    @PostMapping("")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject insertPayment(@RequestBody Payment payment) {
        return paymentService.insertPayment(payment);
    }
}
