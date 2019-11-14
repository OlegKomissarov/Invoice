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
@Table(name = "user", schema = "invoice")
@SequenceGenerator(name = "user_id_seq")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, length = 224)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "current_balance", nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "phone", nullable = false, length = 17)
    private String phone;

    public User(Integer id) {
        this.id = id;
    }

    public User(String email, String name, String phone) {
        this.email = email;
        this.username = name;
        this.phone = phone;
    }
}
