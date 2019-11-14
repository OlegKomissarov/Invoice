package by.bsu.invoice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    @JsonBackReference
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Payment(Invoice invoice) {
        this.invoice = invoice;
        this.date = new Date();
        this.payment = new BigDecimal(0);
    }
}
