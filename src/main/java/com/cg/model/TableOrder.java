package com.cg.model;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.model.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "table_order_id")
public class TableOrder extends BaseEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column
    private String title;
     @Enumerated(EnumType.STRING)
    private EStatus status;


    public TableOrderDTO toTableOrderDTO() {
        return new TableOrderDTO()
                .setId(id)
                .setTitle(title)
                .setStatus(status.getValue())
                ;
    }
}
