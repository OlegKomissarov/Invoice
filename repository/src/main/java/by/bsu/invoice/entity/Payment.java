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
    @Column(name = "payment", nullable = false)
    private BigDecimal payment;

    @Column(name = "date", nullable = false)
    private Date date;
}
