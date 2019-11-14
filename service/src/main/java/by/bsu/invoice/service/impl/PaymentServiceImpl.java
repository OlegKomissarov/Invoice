package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.Payment;
import by.bsu.invoice.repository.PaymentRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Transactional
@Service
public class PaymentServiceImpl extends AbstractService<Payment, PaymentRepository> implements PaymentService {

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        super(repository);
    }

    public void create (Invoice invoice) {
        Payment payment = new Payment(invoice);
        repository.save(payment);
    }

    @Override
    public void update(Integer id, Date date, BigDecimal paymentQuantity) {
        Payment payment = getById(id);
        payment.setDate(date);
        payment.setPayment(paymentQuantity);
        repository.save(payment);
    }

    public void delete (Integer id) {
        repository.delete(getById(id));
    }
}
