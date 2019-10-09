package by.bsu.invoice.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice", schema = "invoice")
@SequenceGenerator(name = "invoice_id_seq")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "payment_deadline", nullable = false)
    private Date paymentDeadline;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToMany(mappedBy = "invoice")
    @Fetch(FetchMode.JOIN)
    private List<Expense> expenses;

    @OneToMany(mappedBy = "invoice")
    @Fetch(FetchMode.JOIN)
    private List<Payment> payments;

    @Column(name = "is_archival_for_customer", nullable = false)
    private boolean isArchivalForCustomer;

    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;

    @Column(name = "is_archival_for_seller", nullable = false)
    private boolean isArchivalForSeller;

    private BigDecimal totalPrice;

    private BigDecimal paymentsSum;

    public Invoice(final long newId, final String newTitle,
                   final String newDescription, final Date newPaymentDeadline,
                   final User newSeller, final User newCustomer, final boolean newIsApproved) {
        this.id = newId;
        this.title = newTitle;
        this.description = newDescription;
        this.paymentDeadline = newPaymentDeadline;
        this.seller = newSeller;
        this.customer = newCustomer;
        this.expenses = new LinkedList<>();
        this.payments = new LinkedList<>();
        this.isApproved = newIsApproved;
    }

    public void addExpense(final Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(final Expense expense) {
        expenses.remove(expense);
    }

    public Expense getExpense(final int index) {
        return expenses.get(index);
    }

    public void addPayment(final Payment payment) {
        payments.add(payment);
    }

    public void removePayment(final Payment payment) {
        payments.remove(payment);
    }

    public Payment getPayment(final int index) {
        return payments.get(index);
    }

    public BigDecimal calculateInvoiceOverdraft() {
        BigDecimal overdraft;
        if(paymentsSum != null) {
            overdraft = totalPrice.subtract(paymentsSum);
        } else {
            overdraft = totalPrice;
        }
        return overdraft;
    }
}
