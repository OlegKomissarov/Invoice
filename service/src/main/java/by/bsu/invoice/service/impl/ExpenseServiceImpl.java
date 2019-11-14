package by.bsu.invoice.service.impl;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.entity.Invoice;
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

    public void create (Invoice invoice) {
        Expense expense = new Expense(invoice);
        repository.save(expense);
    }

    @Override
    public void update(Integer id, String description, Integer count, Integer price) {
        Expense expense = getById(id);
        expense.setDescription(description);
        expense.setCount(count);
        expense.setPrice(price);
        repository.save(expense);
    }

    public void delete (Integer id) {
        repository.delete(getById(id));
    }
}
