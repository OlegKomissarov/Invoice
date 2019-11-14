package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Payment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PaymentRepository extends Repository<Payment> {

    @Query(value = "SELECT * FROM invoice.payment WHERE invoice_id=?", nativeQuery = true)
    List<Payment> getByInvoiceId(Integer invoiceId);
}
