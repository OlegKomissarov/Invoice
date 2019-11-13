package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.User;
import by.bsu.invoice.repository.InvoiceRepository;
import by.bsu.invoice.repository.UserRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.ExpenseService;
import by.bsu.invoice.service.InvoiceService;
import by.bsu.invoice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Transactional
@Service
public class InvoiceServiceImpl extends AbstractService<Invoice, InvoiceRepository> implements InvoiceService {

    private UserService userService;
    private ExpenseService expenseService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository repository, UserService userService, ExpenseService expenseService) {
        super(repository);
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @Override
    public List<Invoice> getByUserId(long userId) {
        return repository.getByUserId(userId);
    }

    @Override
    public long[] getSellerAndCustomerId(long invoiceId) {
        return repository.getSellerAndCustomerId(invoiceId);
    }

    @Override
    public void create(String title,
                       String description,
                       Date paymentDeadline,
                       long sellerId,
                       String customerEmail,
                       long customerId,
                       String[] productIds,
                       String[] productAmounts
    ) {
        User customer = new User();
        User seller = new User();
        boolean isApproved = false;
        seller.setId(sellerId);
        if (customerEmail != null) {
            customerId = userService.getIdByEmail(customerEmail);
            isApproved = true;
        }
        if (customerId != -1) {
            customer.setId(customerId);
            Invoice invoice = new Invoice(title, description, paymentDeadline,
                    seller, customer, isApproved);
            for (int i = 0; i < productIds.length; i++) {
                expenseService.create(invoice.getId(), Long.parseLong(productIds[i]),
                        Integer.parseInt(productAmounts[i]));
            }
            repository.save(invoice);
        }
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public void approve(long id) {
        repository.approve(id);
    }

//    @Override
//    public List<Invoice> getByTitleAndUserId(String title, long userId) {
//        return repository.getByTitleAndUserId(title, userId);
//    }
}
