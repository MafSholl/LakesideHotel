package com.lakesidehotel.app.manager.model;

import com.lakesidehotel.app.users.model.User;
import com.lakesidehotel.app.users.model.UserType;
import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Manager extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private UserType staffType;
}
