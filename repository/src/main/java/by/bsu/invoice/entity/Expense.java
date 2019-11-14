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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "amount", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
