package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Payment;
import by.bsu.invoice.repository.PaymentRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PaymentServiceImpl extends AbstractService<Payment, PaymentRepository> implements PaymentService {

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        super(repository);
    }

    public void create (Integer invoiceId, Integer productId, Integer productAmount) {
//        Payment payment = new Payment(new Invoice(invoiceId), new Product(productId), productAmount);
//        repository.save(payment);
    }

    public List<Payment> getByInvoiceId(Integer invoiceId){
        return repository.getByInvoiceId(invoiceId);
    }
}
