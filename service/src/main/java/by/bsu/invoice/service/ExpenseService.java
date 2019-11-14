package by.bsu.invoice.service;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.entity.Invoice;

public interface ExpenseService extends Service<Expense> {
    void create(Invoice invoice);

    void update(Integer id, String description, Integer count, Integer price);

    void delete(Integer id);
}
