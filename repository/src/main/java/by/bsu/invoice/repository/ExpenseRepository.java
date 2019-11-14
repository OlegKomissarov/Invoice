package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Expense;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ExpenseRepository extends Repository<Expense> {

    @Query(value = "SELECT * FROM invoice.expense WHERE invoice_id=?", nativeQuery = true)
    List<Expense> getByInvoiceId(Integer invoiceId);
}
