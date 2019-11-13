package by.bsu.invoice.repository.impl;

import by.bsu.invoice.entity.Expense;
import by.bsu.invoice.repository.AbstractRepository;
import by.bsu.invoice.repository.ExpenseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("expenseRepository")
public class ExpenseRepositoryImpl extends AbstractRepository<Expense> implements ExpenseRepository {

    public ExpenseRepositoryImpl() {
        super.setClazz(Expense.class);
    }

    @Override
    public List<Expense> getAll() {
        // TODO: move to abstract class
        // TODO: add to controller
        return (List<Expense>) entityManager.createNativeQuery("SELECT * FROM expense").getResultList();
    }

    @Override
    public List<Expense> getByInvoiceId(long invoiceId) {
        // TODO: add to controller
        return (List<Expense>) entityManager.createNativeQuery("SELECT product.title AS product_title, "
                + "product.description AS product_description, "
                + "expense.amount, (price*amount) AS expense_price "
                + "FROM expense JOIN product ON product_id=product.id "
                + "WHERE invoice_id=?").setParameter(1, invoiceId).getResultList();
    }
}
