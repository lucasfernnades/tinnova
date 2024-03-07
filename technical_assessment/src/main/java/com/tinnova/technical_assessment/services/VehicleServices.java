package com.tinnova.technical_assessment.services;

import com.tinnova.technical_assessment.dto.RequestDTO;
import com.tinnova.technical_assessment.dto.ResponseDTO;
import com.tinnova.technical_assessment.entities.Vehicle;
import com.tinnova.technical_assessment.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class VehicleServices {

    private final VehicleRepository repository;

    private static final String ERROR_MESSAGE = "some error happened during the request!";

    private static final String INVALID_MAKE = "the make inserted isn't valid, fix the name and try again!";

    @Autowired
    public VehicleServices(VehicleRepository repository) {
        this.repository = repository;
    }

    public ResponseDTO getAllVehicleWithParametersOrNot(String make, Integer releaseYear, String color) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            List<Vehicle> vehicles;

            if (!StringUtils.containsWhitespace(make) && !StringUtils.containsWhitespace(color) && releaseYear == null)
                vehicles = repository.findAll();
            else
                vehicles = repository.getAllVehicleWithParameters(make, releaseYear, color);

            if (vehicles.isEmpty())
                response.setMessage("the request was success but don't have register to return");
            else
                response.setMessage("success to find all vehicles!");

            response.setData(vehicles);
            response.setSuccess(true);
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO getVehicleById(Long id) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            response.setData(repository.findById(id).orElseThrow());
            response.setSuccess(true);
            response.setMessage("success to find vehicle by id informed!");
        }
        catch (NoSuchElementException ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO getCountUnsoldVehicles() {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            response.setData(repository.getCountUnsoldVehicles());
            response.setSuccess(true);
            response.setMessage("success to count the unsold vehicles!");
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO getCountVehiclesByDecade(Integer decade) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        int decadeStart = decade - (decade % 10);
        int decadeEnd = decadeStart + 10;

        try {
            response.setData(repository.getCountVehiclesByDecade(decadeStart, decadeEnd));
            response.setSuccess(true);
            response.setMessage("success to count the decade vehicles!");
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO getCountByMake(String make) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            response.setData(repository.getCountByMake(make));
            response.setSuccess(true);
            response.setMessage("success to count the vehicles by the make!");
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO getAllRegisteredVehiclesAtLastWeek() {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            response.setData(repository.getAllRegisteredVehiclesAtLastWeek());
            response.setSuccess(true);
            response.setMessage("success to find all vehicles registered at last week!");
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO registerVehicle(RequestDTO dto) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        Vehicle vehicle = new Vehicle(null, dto.getModel().toLowerCase(), dto.getMake().toLowerCase(),
                dto.getReleaseYear(), dto.getColor().toLowerCase(),false, LocalDateTime.now(), null);

        if (!vehicle.validMakesCar(dto.getMake())) {
            response.setSuccess(false);
            response.setMessage(INVALID_MAKE);
            return response;
        }

        try {
            response.setData(repository.save(vehicle));
            response.setSuccess(true);
            response.setMessage("success to register the vehicle!");
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO updateAllVehicleInfo(Long id, RequestDTO dto) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            Vehicle vehicle = repository.findById(id).orElseThrow();

            if (!vehicle.validMakesCar(dto.getMake())) {
                response.setSuccess(false);
                response.setMessage(INVALID_MAKE);
                return response;
            }

            vehicle.setModel(dto.getModel().toLowerCase());
            vehicle.setMake(dto.getMake().toLowerCase());
            vehicle.setReleaseYear(dto.getReleaseYear());
            vehicle.setColor(dto.getColor().toLowerCase());
            vehicle.setSold(dto.getSold());
            vehicle.setUpdated(LocalDateTime.now());

            response.setData(repository.save(vehicle));
            response.setSuccess(true);
            response.setMessage("success to update all info of the vehicle!");
        }
        catch (NoSuchElementException ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO updateSomeVehicleInfo(Long id, RequestDTO dto) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            Vehicle vehicle = repository.findById(id).orElseThrow();

            if (!dto.getMake().isEmpty() && !vehicle.validMakesCar(dto.getMake())) {
                response.setSuccess(false);
                response.setMessage(INVALID_MAKE);
                return response;
            }

            vehicle.setModel(dto.getModel() == null ? vehicle.getModel() : dto.getModel().toLowerCase());
            vehicle.setMake(dto.getMake() == null || dto.getMake().isEmpty() ? vehicle.getMake() : dto.getMake().toLowerCase());
            vehicle.setReleaseYear(dto.getReleaseYear() == null ? vehicle.getReleaseYear() : dto.getReleaseYear());
            vehicle.setColor(dto.getColor() == null ? vehicle.getColor() : dto.getColor().toLowerCase());
            vehicle.setSold(dto.getSold() == null ? vehicle.getSold() : dto.getSold());
            vehicle.setUpdated(LocalDateTime.now());

            response.setData(repository.save(vehicle));
            response.setSuccess(true);
            response.setMessage("success to update some info of the vehicle!");
        }
        catch (NoSuchElementException ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }

    public ResponseDTO removeVehicle(Long id) {
        String idResponse = UUID.randomUUID().toString();

        ResponseDTO response = new ResponseDTO();
        response.setId(idResponse);

        try {
            repository.deleteById(id);
            response.setSuccess(true);
            response.setMessage("success to remove the vehicle by id");
        }
        catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage(ERROR_MESSAGE);
        }

        return response;
    }
}
