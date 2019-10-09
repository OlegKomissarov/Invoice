package by.bsu.invoice.repository;

import by.bsu.invoice.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository extends Repository<User> {

    User getByEmail(final String email);

    BigDecimal getBalance(final long userId);

    BigDecimal updateBalance(final BigDecimal currentBalance, final BigDecimal moneyAmount, final long userId);

    BigDecimal getInvoiceSummary(final long customerId);

    BigDecimal getPaymentSummary(final long customerId);

    List<User> getByQuery(final String searchQuery);
}
