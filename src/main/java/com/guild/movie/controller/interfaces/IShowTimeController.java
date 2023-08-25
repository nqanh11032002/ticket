package com.guild.movie.controller.interfaces;

import com.guild.movie.dto.ShowTimeDTO;
import com.guild.movie.response.ResponseObject;

import java.util.Date;

public interface IShowTimeController {

    // API for ADMIN role
    public ResponseObject insertShowTime (ShowTimeDTO showTimeDTO);
    public ResponseObject deleteShowTime (Integer id);
    public ResponseObject listShowTimesAdmin(String roomId, Date date);

    // API for USER role
    public ResponseObject listShowTimesCustomer(Integer movieId, Date date);
}
