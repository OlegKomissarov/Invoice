package by.bsu.invoice.repository.impl;

import by.bsu.invoice.entity.Payment;
import by.bsu.invoice.repository.AbstractRepository;
import by.bsu.invoice.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("paymentRepository")
public class PaymentRepositoryImpl extends AbstractRepository<Payment> implements PaymentRepository {

    public PaymentRepositoryImpl() {
        super.setClazz(Payment.class);
    }

    @Override
    public List<Payment> getAll() {
        // TODO: add to controller
        // TODO: move to abstract class
        return (List<Payment>) entityManager.createNativeQuery("SELECT * FROM payment").getResultList();
    }

    @Override
    public List<Payment> getByInvoiceId(long invoiceId) {
        // TODO: add to controller
        return (List<Payment>) entityManager
                .createNativeQuery("SELECT id, payment, date FROM payment WHERE invoice_id=?")
                .setParameter(1, invoiceId).getResultList();
    }
}
