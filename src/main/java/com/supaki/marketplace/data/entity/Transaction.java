package com.supaki.marketplace.data.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="transaction_details")
public class Transaction {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="buyer_id")
    private long buyerId;

    @Column(name="seller_id")
    private long sellerId;

    @Column(name="total_amount")
    private double totalAmount;

    @Column(name="seller_amount")
    private double sellerAmount;

    @Column(name="commission_amount")
    private double commissionAmount;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;


}
