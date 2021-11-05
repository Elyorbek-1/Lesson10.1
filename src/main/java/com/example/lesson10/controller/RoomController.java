package com.example.lesson10.controller;

import com.example.lesson10.entity.Hotel;
import com.example.lesson10.entity.Room;
import com.example.lesson10.payload.RoomDto;
import com.example.lesson10.repository.HotelRepository;
import com.example.lesson10.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping
    public Page<Room> get(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,0);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }
    @PostMapping
    public String add(@RequestBody RoomDto roomDto){
        Room room=new Room();
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "Bunday id li hotel mavjud emas";
        }
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Bajarildi";
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return "Bunday id li room mavjud emas";
        }
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "Bunday id li hotel mavjud emas";
        }
        Room room = optionalRoom.get();
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        room.setFloor(roomDto.getFloor());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Bajarildi";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        try {
            roomRepository.deleteById(id);
        } catch (Exception e) {
            return "Malumot o'chirilmadi";
        }
        return "Bajarildi";
    }
}
