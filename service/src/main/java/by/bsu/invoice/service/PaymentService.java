package by.bsu.invoice.service;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.Payment;

import java.math.BigDecimal;
import java.util.Date;

public interface PaymentService extends Service<Payment> {
    void create(Invoice invoice);

    void update(Integer id, Date date, BigDecimal payment);

    void delete(Integer id);
}
