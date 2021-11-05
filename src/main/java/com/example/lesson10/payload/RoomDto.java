package com.example.lesson10.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private String floor;
    private long size;
    private Integer hotelId;
}
