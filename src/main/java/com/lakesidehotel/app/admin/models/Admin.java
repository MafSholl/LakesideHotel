package com.lakesidehotel.app.admin.models;

import com.lakesidehotel.app.users.model.User;
import com.lakesidehotel.app.users.model.UserType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Admin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private UserType staffType;

}
