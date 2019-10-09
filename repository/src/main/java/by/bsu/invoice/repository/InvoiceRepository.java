package by.bsu.invoice.repository;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.User;

import java.util.List;

public interface InvoiceRepository extends Repository<Invoice> {

    List<Invoice> getBySellerId(final long sellerId);

    long[] getSellerAndCustomerId(final long id);

    void delete(final long id, final boolean isSeller);

    void approve(final long id);

    List<User> getByQuery(final String searchQuery);
}
