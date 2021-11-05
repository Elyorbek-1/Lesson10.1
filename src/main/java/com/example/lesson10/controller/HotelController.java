package com.example.lesson10.controller;

import com.example.lesson10.entity.Hotel;
import com.example.lesson10.entity.Room;
import com.example.lesson10.repository.HotelRepository;
import com.example.lesson10.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/hotelId/{hotelId}")
    public Page<Room> getRoomsByHotelId(@RequestParam int page,@PathVariable Integer hotelId){
        Pageable pageable=PageRequest.of(page,10);
        Page<Room> pagebleRoomByHotelId = roomRepository.findByHotel_Id(hotelId, pageable);
        return pagebleRoomByHotelId;
    }
    @GetMapping
    public Page<Hotel> get(@RequestParam int page){
        Pageable pageable=PageRequest.of(page,10);
        return hotelRepository.findAll(pageable);
    }
    @PostMapping
    public String add(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Bajarildi";
    }
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return "Bunday id li malumot yo'q";
        }
        Hotel hotel1 = optionalHotel.get();
        hotel1.setHotelName(hotel.getHotelName());
        hotelRepository.save(hotel1);
        return "Bajarildi";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        try {
            hotelRepository.deleteById(id);
        } catch (Exception e) {
            return "O'chirish mumkun emas";
        }
        return "Bajarildi";
    }
}
