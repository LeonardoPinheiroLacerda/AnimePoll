package com.leonardo.animepoll.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.leonardo.animepoll.models.enums.Rating;
import com.leonardo.animepoll.models.enums.Season;
import com.leonardo.animepoll.models.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString

@Entity
@Table(name = "ANIMES")
public class Anime {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anime_seq")
    @SequenceGenerator(
        name = "anime_seq", sequenceName = "anime_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String japaneseTitle;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private LocalDate airedFrom;

    @Column(nullable = true)
    private LocalDate airedTo;

    @Column(nullable = false)
    private String url;

    @Column(nullable = true)
    private String trailer;

    @Column(nullable = false)
    private Long votes;

    @Column(nullable = false)
    private Long members;

    @Column(nullable = true)
    private Float score;

    @Column(nullable = true)
    private Long scoredBy;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Season season;

}
