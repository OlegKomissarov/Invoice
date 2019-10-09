package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Expense;

import java.util.List;

public interface ExpenseRepository extends Repository<Expense> {

    List<Expense> getByInvoiceId(final long invoiceId);
}
