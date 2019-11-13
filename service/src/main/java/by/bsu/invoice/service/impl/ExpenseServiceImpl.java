package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.Product;
import by.bsu.invoice.repository.ExpenseRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ExpenseServiceImpl extends AbstractService<Expense, ExpenseRepository> implements ExpenseService {

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository) {
        super(repository);
    }

    public void create (long invoiceId, long productId, long productAmount) {
        Expense expense = new Expense(new Invoice(invoiceId), new Product(productId), productAmount);
        repository.save(expense);
    }
}
