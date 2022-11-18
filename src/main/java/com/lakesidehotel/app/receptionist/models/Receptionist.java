package com.lakesidehotel.app.receptionist.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receptionist{
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "User_Id_Generator",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "id_generator",
            strategy = GenerationType.SEQUENCE
    )
    @Id
    private Long id;
}
