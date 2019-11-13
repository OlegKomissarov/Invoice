package by.bsu.invoice.repository;

import by.bsu.invoice.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository extends Repository<User> {

    User getByEmail(String email);

    long getIdByEmail(String email);

    BigDecimal getBalance(long userId);

    BigDecimal updateBalance(BigDecimal currentBalance, BigDecimal moneyAmount, long userId);

    BigDecimal getInvoiceSummary(long customerId);

    BigDecimal getPaymentSummary(long customerId);

    List<User> getBySearchQuery(String searchQuery);
}
