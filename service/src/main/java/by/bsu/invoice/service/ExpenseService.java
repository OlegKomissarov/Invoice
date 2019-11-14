package by.bsu.invoice.service;

import by.bsu.invoice.entity.Expense;

import java.util.List;

public interface ExpenseService extends Service<Expense> {
    void create(Integer invoiceId);

    List<Expense> getByInvoiceId(Integer invoiceId);
}
