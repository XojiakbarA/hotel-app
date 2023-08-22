package uz.pdp.hotelapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelapp.model.Room;
import uz.pdp.hotelapp.repository.RoomRepository;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/{id}/rooms")
    public Page<Room> getAllRoomByHotelId(@PathVariable Long id,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAllByHotelId(id, pageable);
    }
}
