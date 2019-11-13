package by.bsu.invoice.repository.impl;

import by.bsu.invoice.entity.User;
import by.bsu.invoice.repository.AbstractRepository;
import by.bsu.invoice.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository("userRepository")
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    public UserRepositoryImpl() {
        super.setClazz(User.class);
    }

    @Override
    public List<User> getAll() {
        // TODO: add to controller
        // TODO: move to abstract class
        return (List<User>) entityManager.createNativeQuery("SELECT * FROM user").getResultList();
    }

    @Override
    public User getByEmail(String email) {
        // TODO: add to controller
        return (User) entityManager
                .createNativeQuery("SELECT * FROM invoice.user WHERE email=?")
                .setParameter(1, email).getSingleResult();
    }

    @Override
    public long getIdByEmail(String email) {
        return (long) entityManager
                .createNativeQuery("SELECT id FROM invoice.user WHERE email=?")
                .setParameter(1, email).getSingleResult();
    }

    @Override
    public BigDecimal getBalance(long userId) {
        // TODO: add to controller
        return (BigDecimal) entityManager
                .createNativeQuery("SELECT current_balance FROM user WHERE id=?")
                .setParameter(1, userId).getSingleResult();
    }

    @Override
    public BigDecimal updateBalance(BigDecimal currentBalance, BigDecimal moneyAmount, long userId) {
        // TODO: add to controller
        return (BigDecimal) entityManager
                .createNativeQuery("UPDATE user SET current_balance=? WHERE id=?")
                .setParameter(1, currentBalance).setParameter(2, userId).getSingleResult();
    }

    @Override
    public BigDecimal getInvoiceSummary(long customerId) {
        // TODO: add to controller
        return (BigDecimal) entityManager
                .createNativeQuery("SELECT SUM(exp.total_price) AS invoices_summary FROM invoice JOIN " +
                        "(SELECT price*amount AS total_price, expense.invoice_id FROM expense JOIN product " +
                        "ON product_id=product.id) AS exp ON invoice.id=exp.invoice_id " +
                        "WHERE customer_id=?")
                .setParameter(1, customerId).getSingleResult();
    }

    @Override
    public BigDecimal getPaymentSummary(long customerId) {
        // TODO: add to controller
        return (BigDecimal) entityManager
                .createNativeQuery("SELECT SUM(payment) AS payments_summary FROM invoice " +
                        "JOIN payment ON invoice.id=payment.invoice_id WHERE customer_id=?")
                .setParameter(1, customerId).getSingleResult();
    }

    @Override
    public List<User> getBySearchQuery(String searchQuery) {
        return (List<User>) entityManager
                .createNativeQuery("SELECT id, email, name FROM user WHERE user.name LIKE CONCAT('%', ?, '%') " +
                        "OR email LIKE CONCAT('%', ?, '%') ORDER BY name;")
                .setParameter(1, searchQuery).setParameter(2, searchQuery).getSingleResult();
    }
}