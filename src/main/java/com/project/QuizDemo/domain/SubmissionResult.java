package com.project.QuizDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResult {

    private String date;
    private int id;
    private String categoryName;
    private String userFullname;
    private int qNum;
    private int score;
}
