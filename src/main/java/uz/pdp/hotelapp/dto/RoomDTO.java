package uz.pdp.hotelapp.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private Integer number;
    private Integer floor;
    private Float size;
    private Long hotelId;
}
