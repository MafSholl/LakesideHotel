package com.lakesidehotel.app.bill.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bill {
    @Id
    private Long billNo;
    private String customerFirstName;
    private String customerLastName;
    private Long customerId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ItemBillPerCustomer",
            joinColumns = @JoinColumn(
                    name = "customerId", referencedColumnName = "customerId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "itemId", referencedColumnName = "itemId"
            )
    )
    private List<ItemMock> itemList = new ArrayList<>();
    private BigDecimal totalAmount;
}
