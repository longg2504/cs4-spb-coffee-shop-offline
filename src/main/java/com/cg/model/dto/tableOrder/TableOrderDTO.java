package com.cg.model.dto.tableOrder;

import com.cg.model.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class TableOrderDTO {
    private Long id;
    private String title;
    private String status;

    public TableOrderDTO(Long id, String title, EStatus status){
        this.id=id;
        this.title=title;
        this.status=status.getValue();
    }


    public TableOrderDTO toTableOrderDTO() {
        return new TableOrderDTO()
                .setId(id)
                .setTitle(title)
                .setStatus(status)
                ;
    }
}
