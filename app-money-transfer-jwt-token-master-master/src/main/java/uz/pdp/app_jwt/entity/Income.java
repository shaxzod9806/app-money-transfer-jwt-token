package uz.pdp.app_jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer fromCardId;

    @Column(nullable = false)
    private Integer toCardId;

    @Column(nullable = false)
    private Double amount;

    @CreationTimestamp
    private Date date;
}
