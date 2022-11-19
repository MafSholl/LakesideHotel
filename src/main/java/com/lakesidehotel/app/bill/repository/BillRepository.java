package com.lakesidehotel.app.bill.repository;

import com.example.lakesidehotel.app.bill.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
