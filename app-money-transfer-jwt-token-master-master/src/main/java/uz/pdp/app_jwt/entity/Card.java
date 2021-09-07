package uz.pdp.app_jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String number;

    private Double balance;

    private Date expiredDate;

    private Boolean active;


}
