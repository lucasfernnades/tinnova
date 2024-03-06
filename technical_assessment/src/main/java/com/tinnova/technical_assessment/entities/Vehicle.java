package com.tinnova.technical_assessment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private String make;

    private Integer releaseYear;

    private String color;

    private Boolean sold;

    private LocalDateTime created;

    private LocalDateTime updated;

    public boolean validMakesCar(String makeToValidate) {
        Set<String> makesCar = new HashSet<>(Set.of(
                "abarth", "aiways", "alfa romeo", "alpine", "aston martin", "audi", "bentley", "bmw", "byd",
                "caoa chery", "chevrolet", "chrysler", "citroen", "cupra", "dacia", "dodge", "ds", "effa",
                "ferrari", "fiat", "fisker", "ford", "foton", "great wall", "haval", "honda", "husqvarna",
                "hyundai", "iveco", "jac", "jaguar", "jeep", "kia", "lamborghini", "land rover", "lexus",
                "lifan", "lotus", "maserati", "mazda", "mclaren", "mercedes-amg", "mercedes-benz", "mini",
                "mitsubishi", "nissan", "opel", "ora", "peugeot", "polestar", "porsche", "ram", "renault",
                "rolls-royce", "royal enfield", "seat", "seres", "subaru", "suzuki", "skoda", "smart", "tesla",
                "toyota", "triumph", "troller", "volkswagen", "volvo", "yamaha"
        ));

        return makesCar.contains(makeToValidate.toLowerCase());
    }
}
