package by.bsu.invoice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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
    private Integer id;

    @Column(name = "created_date", nullable = false)
    private Date date;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "invoice_number", nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_archival", nullable = false)
    private Integer isArchival;

    public Invoice(Date date, String title, Integer number, User user, Integer isArchival) {
        this.date = date;
        this.title = title;
        this.number = number;
        this.user = user;
        this.isArchival = isArchival;
    }
}
