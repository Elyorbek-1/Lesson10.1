package com.example.lesson10.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private String floor;
    private long size;
    @ManyToOne
    private Hotel hotel;
//    Mehmonxona strukturasini CRUD asosida yozing,
//    bunda Hotel(name), Room(number, floor, size, Hotel) class
//    lar bo'lsin. Hotel id orqali shu mehmonxonaga tegishli roomlar
//    ro'yxatini pageable qilib olib keluvchi method yozing.
//    Proyektni git ga public qilib yuklang va vazifaga javob sifatida
//    shu git repository ning link address ni yuboring.
}
