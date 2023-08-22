package uz.pdp.hotelapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelapp.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
