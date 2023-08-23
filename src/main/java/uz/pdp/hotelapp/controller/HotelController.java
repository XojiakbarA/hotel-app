package uz.pdp.hotelapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelapp.model.Hotel;
import uz.pdp.hotelapp.model.Room;
import uz.pdp.hotelapp.repository.HotelRepository;
import uz.pdp.hotelapp.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public Page<Hotel> getAll(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "20") Integer size) {
        return hotelRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    public Hotel get(@PathVariable Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @GetMapping("/{id}/rooms")
    public Page<Room> getAllRoomByHotelId(@PathVariable Long id,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAllByHotelId(id, pageable);
    }

    @PostMapping
    public String create(@RequestBody Hotel hotel) {
        hotelRepository.save(hotel);
        return "Saved successfully";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Hotel hotel, @PathVariable Long id) {
        Optional<Hotel> optional = hotelRepository.findById(id);
        if (optional.isPresent()) {
            Hotel hotelDB = optional.get();
            hotelDB.setName(hotel.getName());
            hotelRepository.save(hotelDB);
            return "Updated successfully";
        } else {
            return "Hotel not found with id = " + id;
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        roomRepository.deleteAllByHotelId(id);
        hotelRepository.deleteById(id);
    }
}
