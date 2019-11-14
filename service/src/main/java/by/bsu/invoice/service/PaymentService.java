package by.bsu.invoice.service;

import by.bsu.invoice.entity.Payment;

import java.util.List;

public interface PaymentService extends Service<Payment> {
    void create(Integer invoiceId, Integer productId, Integer productAmount);

    List<Payment> getByInvoiceId(Integer invoiceId);
}
