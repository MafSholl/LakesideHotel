package com.lakesidehotel.app.receptionist.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceptionistDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
