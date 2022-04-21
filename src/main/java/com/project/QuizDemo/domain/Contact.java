package com.project.QuizDemo.domain;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Contact {
    private String name;
    private String email;
    private String country;
}
