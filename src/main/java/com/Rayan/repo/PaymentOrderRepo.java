package com.Rayan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rayan.model.PaymentOrder;

public interface PaymentOrderRepo  extends JpaRepository<PaymentOrder, Long>{

}
