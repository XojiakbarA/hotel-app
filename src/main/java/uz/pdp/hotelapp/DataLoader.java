package uz.pdp.hotelapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.hotelapp.model.Hotel;
import uz.pdp.hotelapp.model.Room;
import uz.pdp.hotelapp.repository.HotelRepository;
import uz.pdp.hotelapp.repository.RoomRepository;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void run(String... args) {
        for (int i = 65; i < 74; i++) {
            Hotel hotel = new Hotel();
            hotel.setName(Character.toString((char) i));
            hotelRepository.save(hotel);
        }
        for (int i = 97; i < 597; i++) {
            Room room = new Room();
            Random random = new Random();
            long id = random.ints(1, 8).findFirst().getAsInt();
            Hotel hotel = hotelRepository.findById(id).orElse(null);
            room.setHotel(hotel);
            room.setNumber(i);
            room.setSize((float) i);
            room.setFloor(i);
            roomRepository.save(room);
        }
    }
}
