package com.lakesidehotel.app.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackDto {
    private String giverFirstName;
    private String giverLastName;
    private String feedBack;
    private int starRating;
    private boolean success;
}




