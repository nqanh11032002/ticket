package com.guild.movie.controller;

import com.guild.movie.controller.interfaces.IShowTimeController;
import com.guild.movie.dto.ShowTimeDTO;
import com.guild.movie.response.ResponseObject;
import com.guild.movie.service.interfaces.IShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/showTime")
public class ShowTimeController implements IShowTimeController {
    @Autowired
    private IShowTimeService iShowTimeService;

    @Override
    @PostMapping("/insertShowTime")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject insertShowTime(@RequestBody ShowTimeDTO showTimeDTO) {
        return iShowTimeService.insertShowTime(showTimeDTO);
    }

    @Override
    @DeleteMapping("/deleteShowTime")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject deleteShowTime(@RequestParam("id") Integer id) {
        return iShowTimeService.deleteShowTime(id);
    }

    @Override
    @GetMapping("/listShowTimesAdmin")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseObject listShowTimesAdmin(@RequestParam("roomId") String roomId, @RequestParam("date") Date date) {
        return iShowTimeService.listShowTimeAdmin(roomId, date);
    }

    @Override
    @GetMapping("/listShowTimesCustomer")
    @PreAuthorize("hasRole('client_user')")
    public ResponseObject listShowTimesCustomer(@RequestParam("movieId") Integer movieId, @RequestParam("date") Date date) {
        return iShowTimeService.listShowTimeCustomer(movieId, date);
    }
}
