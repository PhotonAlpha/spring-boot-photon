package com.ethan.common.controller;

import com.ethan.common.model.dto.request.FcFeedbackRequestDto;
import com.ethan.common.model.dto.response.SimpleResponse;
import com.ethan.common.service.FcFeedbackService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @version 1.0
 * @date 07/03/2019
 */
@Api(value = "FeedbackController", description = "Feedback Module", tags = "Feedback")
public class FeedbackController extends BasicController {
    @Autowired
    private FcFeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity feedback(@RequestBody FcFeedbackRequestDto dto) {
        int result = feedbackService.insert(dto, 1L);
        return ResponseEntity.ok(new SimpleResponse(true, "success"));
    }

}
