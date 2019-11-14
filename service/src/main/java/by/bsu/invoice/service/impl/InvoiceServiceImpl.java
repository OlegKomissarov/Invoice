package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.User;
import by.bsu.invoice.repository.InvoiceRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Transactional
@Service
public class InvoiceServiceImpl extends AbstractService<Invoice, InvoiceRepository> implements InvoiceService {

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository repository) {
        super(repository);
    }

    @Override
    public List<Invoice> getByUserId(Integer userId) {
        return repository.getByUserId(userId);
    }

    @Override
    public void create(Date createdDate, String title, Integer invoiceNumber, Integer userId, Integer isArchival) {
        User user = new User(userId);
        Invoice invoice = new Invoice(createdDate, title, invoiceNumber, user, 0);
        repository.save(invoice);
    }

    @Override
    public void update(Integer id, Date createdDate, String title, Integer invoiceNumber) {
        Invoice invoice = getById(id);
        invoice.setDate(createdDate);
        invoice.setTitle(title);
        invoice.setNumber(invoiceNumber);
        repository.save(invoice);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
