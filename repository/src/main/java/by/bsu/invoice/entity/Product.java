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
@Table(name = "product", schema = "invoice")
@SequenceGenerator(name = "product_id_seq")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private long sellerId;

    @Column(name = "is_archival", nullable = false)
    private boolean isArchival;

    public Product(String newTitle, String newDescription, BigDecimal newPrice, long newSellerId) {
        this.title = newTitle;
        this.description = newDescription;
        this.price = newPrice;
        this.sellerId = newSellerId;
    }

    public Product(long newId) {
        this.id = newId;
    }
}
