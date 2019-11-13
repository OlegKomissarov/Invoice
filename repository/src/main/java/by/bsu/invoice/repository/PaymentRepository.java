package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Payment;

import java.util.List;

public interface PaymentRepository extends Repository<Payment> {

    List<Payment> getByInvoiceId(long invoiceId);
}
