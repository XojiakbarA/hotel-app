package uz.pdp.hotelapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelapp.dto.RoomDTO;
import uz.pdp.hotelapp.model.Hotel;
import uz.pdp.hotelapp.model.Room;
import uz.pdp.hotelapp.repository.HotelRepository;
import uz.pdp.hotelapp.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public Page<Room> getAll(@RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "20") Integer size) {
        return roomRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Room get(@PathVariable Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @PostMapping
    public String create(@RequestBody RoomDTO dto) {
        Room room = new Room();

        String res = setAttributes(dto, room);

        if (res != null) {
            return res;
        }

        roomRepository.save(room);

        return "Saved successfully";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody RoomDTO dto, @PathVariable Long id) {
        Optional<Room> optional = roomRepository.findById(id);

        if (optional.isEmpty()) {
            return "Room not found with id = " + id;
        }

        String res = setAttributes(dto, optional.get());

        if (res != null) {
            return res;
        }

        roomRepository.save(optional.get());

        return "Saved successfully";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomRepository.deleteById(id);
    }

    private String setAttributes(RoomDTO dto, Room room) {
        if (dto.getNumber() != null) {
            room.setNumber(dto.getNumber());
        }
        if (dto.getFloor() != null) {
            room.setFloor(dto.getFloor());
        }
        if (dto.getSize() != null) {
            room.setSize(dto.getSize());
        }
        if (dto.getHotelId() != null) {
            Optional<Hotel> optional = hotelRepository.findById(dto.getHotelId());
            if (optional.isEmpty()) {
                return "Hotel not found with id = " + dto.getHotelId();
            }
            room.setHotel(optional.get());
        }
        return null;
    }
}
