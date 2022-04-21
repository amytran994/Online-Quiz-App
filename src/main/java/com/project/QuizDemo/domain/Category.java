package com.project.QuizDemo.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "category")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
