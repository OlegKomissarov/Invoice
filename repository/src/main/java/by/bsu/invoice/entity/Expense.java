package by.bsu.invoice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "amount", nullable = false)
    private int serviceAmount;

    private BigDecimal totalPrice;
}
