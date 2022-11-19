package com.lakesidehotel.app.feedback.repository;

import com.lakesidehotel.app.feedback.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
