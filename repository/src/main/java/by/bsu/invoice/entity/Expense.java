package by.bsu.invoice.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expense", schema = "invoice")
@SequenceGenerator(name = "expense_id_seq")
public class Expense {
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount", nullable = false)
    private long productAmount;
}
