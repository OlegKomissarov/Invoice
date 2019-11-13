package by.bsu.invoice.service;

import by.bsu.invoice.entity.Expense;

public interface ExpenseService extends Service<Expense> {
    void create(long invoiceId, long productId, long productAmount);
}
