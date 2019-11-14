package by.bsu.invoice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment", schema = "invoice")
@SequenceGenerator(name = "payment_id_seq")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "created_date", nullable = false)
    private Date date;

    @Column(name = "payment", nullable = false)
    private BigDecimal payment;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
