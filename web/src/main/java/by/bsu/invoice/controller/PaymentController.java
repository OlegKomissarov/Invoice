package by.bsu.invoice.controller;

import by.bsu.invoice.entity.Payment;
import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.service.PaymentService;
import by.bsu.invoice.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private PaymentService paymentService;
    private SessionUser sessionUser;

    @Autowired
    public PaymentController(PaymentService paymentService, SessionUser sessionUser) {
        this.paymentService = paymentService;
        this.sessionUser = sessionUser;
    }

    @PostMapping()
    public ResponseEntity<Payment> create(@RequestBody Invoice invoice) {
        paymentService.create(invoice);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(@PathVariable Integer id, @RequestBody Payment payment) {
        paymentService.update(
                id,
                payment.getDate(),
                payment.getPayment()
        );
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> delete(@PathVariable Integer id) {
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
