package com.project.QuizDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizResult {

    private String question;
    private int qnum;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;

    private int checked;
    private int correctOpt;
    private boolean correct;


}
