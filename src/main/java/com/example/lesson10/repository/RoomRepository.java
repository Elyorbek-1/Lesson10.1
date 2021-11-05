package com.example.lesson10.repository;

import com.example.lesson10.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findByHotel_Id(Integer hotel_id, Pageable pageable);
}
