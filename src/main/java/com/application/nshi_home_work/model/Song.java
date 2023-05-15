package com.application.nshi_home_work.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private Long id;
    private Long artistId;
    private String artistName;

    private String name;

    private Integer auditions;
}
