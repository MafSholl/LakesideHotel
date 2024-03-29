package com.lakesidehotel.app.receptionist.models;

import com.lakesidehotel.app.users.model.User;
import com.lakesidehotel.app.users.model.UserType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receptionist extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private UserType staffType;
}
