package com.cg.model;

import com.cg.model.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column(name = "total_price")
    private BigDecimal totalPrice;

     @OneToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",nullable = false)
    private Order order;
}
