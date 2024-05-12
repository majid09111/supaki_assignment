package com.supaki.marketplace.data.entity;

import com.supaki.marketplace.data.enums.ProductType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="product_details")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name="name")
    private String name;

    @Column(name="quantity")
    private long quantity;

    @Column(name="user_id")
    private long userId;

    @Column(name="price")
    private long price;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
