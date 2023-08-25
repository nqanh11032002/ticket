package com.guild.movie.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeDTO {
    private Integer id;

    private String startTime;

    private String endTime;

    private String roomId;

    @Temporal(TemporalType.DATE)
    private Date dateShow;

    private Integer movieId;
}
