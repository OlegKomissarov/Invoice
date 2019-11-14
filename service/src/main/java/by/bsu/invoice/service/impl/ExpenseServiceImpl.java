package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.repository.ExpenseRepository;
import by.bsu.invoice.service.AbstractService;
import by.bsu.invoice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ExpenseServiceImpl extends AbstractService<Expense, ExpenseRepository> implements ExpenseService {

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository repository) {
        super(repository);
    }

    public void create (Integer invoiceId) {
//        repository.save(expense);
    }

    public List<Expense> getByInvoiceId(Integer invoiceId){
        return repository.getByInvoiceId(invoiceId);
    }
}
