package by.bsu.invoice.repository.impl;

import by.bsu.invoice.entity.Invoice;
import by.bsu.invoice.entity.User;
import by.bsu.invoice.repository.AbstractRepository;
import by.bsu.invoice.repository.InvoiceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


@Repository("invoiceRepository")
public class InvoiceRepositoryImpl extends AbstractRepository<Invoice> implements InvoiceRepository {

    public InvoiceRepositoryImpl() {
        super.setClazz(Invoice.class);
    }

    @Override
    public List<Invoice> getAll() {
        // TODO: move to abstract class
        // TODO: add to controller
        return (List<Invoice>) entityManager.createNativeQuery("SELECT * FROM invoice").getResultList();
    }

    @Override
    public List<Invoice> getByUserId(long userId) {
        // TODO: make response like [{key: value}, {key: value}]
        return (List<Invoice>) entityManager.createNativeQuery(
                "SELECT invoice.id, invoice.title, SUM(price*amount) AS total_price, " +
                        "invoice.is_approved, invoice.customer_id, customer.name AS customer_name, " +
                        "invoice.seller_id, seller.name AS seller_name " +
                        "FROM invoice.invoice JOIN invoice.expense ON invoice.invoice.id=invoice.expense.invoice_id " +
                        "JOIN invoice.product ON invoice.expense.product_id=invoice.product.id " +
                        "JOIN invoice.user seller ON invoice.invoice.seller_id=seller.id " +
                        "JOIN invoice.user customer ON invoice.invoice.customer_id=customer.id " +
                        "WHERE (invoice.invoice.seller_id=? AND invoice.invoice.is_archival=0) " +
                        "OR (invoice.invoice.customer_id=? AND invoice.invoice.is_archival=0) " +
                        "GROUP BY invoice.invoice.id, customer_name, seller_name"
        ).setParameter(1, userId).setParameter(2, userId).getResultList();

//        LinkedList<Invoice> invoices = new LinkedList<>();
//        List<Invoice> queryResult = (List<Invoice>) entityManager.createNativeQuery(
//                "SELECT invoice.invoice.id, invoice.invoice.title, SUM(price*amount) AS total_price, " +
//                   "invoice.invoice.is_approved, invoice.invoice.customer_id, customer.name AS customer_name, " +
//                   "invoice.invoice.seller_id, seller.name AS seller_name " +
//                   "FROM invoice.invoice JOIN invoice.expense ON invoice.invoice.id=invoice.expense.invoice_id " +
//                   "JOIN invoice.product ON invoice.expense.product_id=invoice.product.id " +
//                   "JOIN invoice.user seller ON invoice.invoice.seller_id=seller.id " +
//                   "JOIN invoice.user customer ON invoice.invoice.customer_id=customer.id " +
//                   "WHERE (invoice.invoice.seller_id=? AND invoice.invoice.is_archival=0) " +
//                   "OR (invoice.invoice.customer_id=? AND invoice.invoice.is_archival=0) " +
//                   "GROUP BY invoice.invoice.id, customer_name, seller_name"
//        ).setParameter(1, userId).setParameter(2, userId).getResultList();
//
//        queryResult.forEach(queryInvoice -> {
//            User seller = new User();
//            seller.setId(queryInvoice.getSeller().getId());
//            seller.setName(queryInvoice.getSeller().getName());
//
//            User customer = new User();
//            customer.setId(queryInvoice.getSeller().getId());
//            customer.setName(queryInvoice.getSeller().getName());
//
//            Invoice invoice = new Invoice();
//            invoice.setId(queryInvoice.getId());
//            invoice.setSeller(seller);
//            invoice.setCustomer(customer);
//            invoice.setTitle(queryInvoice.getTitle());
//            invoice.setTotalPrice(queryInvoice.getTotalPrice());
//            invoices.add(invoice);
//        });
//        return invoices;
    }

    @Override
    public long[] getSellerAndCustomerId(long id) {
        // TODO: Change response. I can't take field of object like userIds[0] or userIds.customerId
        return (long[]) entityManager
                .createNativeQuery("SELECT seller_id, customer_id FROM invoice.invoice WHERE id=?")
                .setParameter(1, id).getSingleResult();
    }

    @Override
    public void delete(long id) {
        // TODO: add to controller
        entityManager.createNativeQuery("UPDATE invoice SET is_archival=1 WHERE id=?")
                .setParameter(1, id).executeUpdate();
    }

    @Override
    public void approve(long id) {
        // TODO: add to controller
        entityManager.createNativeQuery("UPDATE invoice SET is_approved=1 WHERE id=?")
                .setParameter(1, id).executeUpdate();
    }

    // TODO: abandon?
//    @Override
//    public List<Invoice> getByTitleAndUserId(String title, long userId) {
//        return (List<Invoice>) entityManager
//                .createNativeQuery("SELECT invoice.id, invoice.title, SUM(price*amount) AS total_price, invoice.is_approved,  "
//                        + "customer_id, customer.name AS customer_name, "
//                        + "invoice.seller_id, seller.name AS seller_name "
//                        + "FROM invoice JOIN expense ON invoice.id=expense.invoice_id "
//                        + "JOIN product ON product_id=product.id "
//                        + "JOIN user seller ON invoice.seller_id=seller.id "
//                        + "JOIN user customer ON customer_id=customer.id "
//                        + "WHERE invoice.title LIKE CONCAT('%', ?, '%') " +
//                        "AND ((invoice.seller_id=? AND is_archival_for_seller=0) "
//                        + "OR (invoice.customer_id=?  AND is_archival_for_customer=0  AND is_approved=1)) "
//                        + "GROUP BY invoice.id "
//                        + "ORDER BY invoice.title")
//                .setParameter(1, title).setParameter(2, userId).setParameter(3, userId).getResultList();
//    }
}
