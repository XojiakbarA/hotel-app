package uz.pdp.hotelapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelapp.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findAllByHotelId(Long hotelId, Pageable pageable);

    void deleteAllByHotelId(Long hotelId);
}
