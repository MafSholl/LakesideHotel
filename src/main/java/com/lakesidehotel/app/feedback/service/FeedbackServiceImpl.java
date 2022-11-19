package com.lakesidehotel.app.feedback.service;

import com.lakesidehotel.app.feedback.dto.FeedbackDto;
import com.lakesidehotel.app.feedback.models.Feedback;
import com.lakesidehotel.app.feedback.repository.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FeedbackDto addFeedBack(FeedbackDto addFeedBackRequest) {
        Feedback feedback = new Feedback();
        feedback.setGiverFirstName(addFeedBackRequest.getGiverFirstName());
        feedback.setGiverLastName(addFeedBackRequest.getGiverLastName());
        feedback.setFeedBack(addFeedBackRequest.getFeedBack());
        feedback.setStarRating(addFeedBackRequest.getStarRating());

        Feedback savedFeedback = feedbackRepository.save(feedback);
        FeedbackDto feedbackResponse = modelMapper.map(savedFeedback, FeedbackDto.class);
        feedbackResponse.setSuccess(true);
        return feedbackResponse;
    }
}
