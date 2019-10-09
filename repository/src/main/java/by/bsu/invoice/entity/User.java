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
@Table(name = "payment", schema = "invoice")
@SequenceGenerator(name = "payment_id_seq")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email", nullable = false, length = 224)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "current_balance", nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 17)
    private String phone;

    public User(final long newId, final String newEmail, final String newName, final String newPhone) {
        this.id = newId;
        this.email = newEmail;
        this.name = newName;
        this.phone = newPhone;
    }
}
