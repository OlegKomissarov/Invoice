package by.bsu.invoice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "amount", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Expense(Invoice invoice) {
        this.invoice = invoice;
        this.price = 0;
        this.count = 0;
        this.description = "";
    }
}
