package com.cg.model;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.model.dto.tableOrder.TableOrderResDTO;
import com.cg.model.enums.ETableStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class TableOrder extends BaseEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column
    private String title;
     @Enumerated(EnumType.STRING)
    private ETableStatus status;


    public TableOrderDTO toTableOrderDTO() {
        return new TableOrderDTO()
                .setId(String.valueOf(id))
                .setTitle(title)
                .setStatus(status.getValue())
                ;
    }

    public TableOrderResDTO toCreateTableOrderResDTO() {
        return new TableOrderResDTO()
                .setId(null)
                .setTitle(title)
                .setStatus(ETableStatus.EMPTY)
                ;
    }

    public TableOrderResDTO toTableOrderResDTO() {
        return new TableOrderResDTO()
                .setId(id)
                .setTitle(title)
                .setStatus(status)
                ;
    }

    public TableOrderResDTO toUpdateTableOrderResDTO(Long tableOrderId) {
        return new TableOrderResDTO()
                .setId(tableOrderId)
                .setTitle(title)
                .setStatus(ETableStatus.EMPTY)
                ;
    }
}
