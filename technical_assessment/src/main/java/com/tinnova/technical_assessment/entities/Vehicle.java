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
import java.util.ArrayList;
import java.util.List;

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
        List<String> makesCar = new ArrayList<>();
        makesCar.add("Abarth");
        makesCar.add("Aiways");
        makesCar.add("Alfa Romeo");
        makesCar.add("Alpine");
        makesCar.add("Aston Martin");
        makesCar.add("Audi");
        makesCar.add("Bentley");
        makesCar.add("BMW");
        makesCar.add("BYD");
        makesCar.add("CAOA Chery");
        makesCar.add("Chevrolet");
        makesCar.add("Chrysler");
        makesCar.add("Citroen");
        makesCar.add("Cupra");
        makesCar.add("Dacia");
        makesCar.add("Dodge");
        makesCar.add("DS");
        makesCar.add("Effa");
        makesCar.add("Ferrari");
        makesCar.add("Fiat");
        makesCar.add("Fisker");
        makesCar.add("Ford");
        makesCar.add("Foton");
        makesCar.add("Great Wall");
        makesCar.add("Haval");
        makesCar.add("Honda");
        makesCar.add("Husqvarna");
        makesCar.add("Hyundai");
        makesCar.add("Iveco");
        makesCar.add("JAC");
        makesCar.add("Jaguar");
        makesCar.add("Jeep");
        makesCar.add("Kia");
        makesCar.add("Lamborghini");
        makesCar.add("Land Rover");
        makesCar.add("Lexus");
        makesCar.add("Lifan");
        makesCar.add("Lotus");
        makesCar.add("Maserati");
        makesCar.add("Mazda");
        makesCar.add("McLaren");
        makesCar.add("Mercedes-AMG");
        makesCar.add("Mercedes-Benz");
        makesCar.add("Mini");
        makesCar.add("Mitsubishi");
        makesCar.add("Nissan");
        makesCar.add("Opel");
        makesCar.add("Ora");
        makesCar.add("Peugeot");
        makesCar.add("Polestar");
        makesCar.add("Porsche");
        makesCar.add("RAM");
        makesCar.add("Renault");
        makesCar.add("Rolls-Royce");
        makesCar.add("Royal Enfield");
        makesCar.add("SEAT");
        makesCar.add("Seres");
        makesCar.add("Subaru");
        makesCar.add("Suzuki");
        makesCar.add("Skoda");
        makesCar.add("Smart");
        makesCar.add("Tesla");
        makesCar.add("Toyota");
        makesCar.add("Triumph");
        makesCar.add("Troller");
        makesCar.add("Volkswagen");
        makesCar.add("Volvo");
        makesCar.add("Yamaha");

        return makesCar.stream().anyMatch(m -> m.equals(makeToValidate));
    }
}
