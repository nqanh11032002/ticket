package com.guild.movie.service.interfaces;

import com.guild.movie.dto.ShowTimeDTO;
import com.guild.movie.entity.ShowTime;
import com.guild.movie.response.ResponseObject;

import java.util.Date;

public interface IShowTimeService {
    public ResponseObject insertShowTime (ShowTimeDTO showTimeDTO);
    public ResponseObject deleteShowTime (Integer id);
    public ResponseObject listShowTimeAdmin (String roomId, Date date);
    public ResponseObject listShowTimeCustomer(Integer movieId, Date date);

}
