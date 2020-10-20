package com.victorze.secondhandmarket.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor // se podría eliminar?
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    private User owner;

    public Purchase(User owner) {
        this.owner = owner;
    }

}
