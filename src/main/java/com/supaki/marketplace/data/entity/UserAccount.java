package com.supaki.marketplace.data.entity;

import com.supaki.marketplace.data.enums.ProductType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="user_id")
    private long userId;

    @Column(name="product_type")
    private ProductType productType;

    @Column(name="product_name")
    private String productName;

    @Column(name="quantity")
    private long quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
