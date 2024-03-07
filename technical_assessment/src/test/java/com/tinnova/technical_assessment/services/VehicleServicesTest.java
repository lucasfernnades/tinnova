package com.tinnova.technical_assessment.services;

import com.tinnova.technical_assessment.dto.RequestDTO;
import com.tinnova.technical_assessment.entities.Vehicle;
import com.tinnova.technical_assessment.repositories.VehicleRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class VehicleServicesTest {

    @Mock
    private VehicleRepository repository;

    @InjectMocks
    private VehicleServices services;

    @Captor
    private ArgumentCaptor<Vehicle> vehicleArgumentCaptor;

    @Nested
    class getAllVehicleWithParametersOrNot {

        @Test
        void shouldReturnAllVehiclesWithoutParameters() {
            var honda = new Vehicle(
                    1L, "civic", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            var bmw = new Vehicle(
                    1L, "x1", "bmw", 2016, "white", false,
                    LocalDateTime.now(), null);

            doReturn(List.of(honda, bmw)).when(repository).findAll();

            var output = services.getAllVehicleWithParametersOrNot(null, null, null);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to find all vehicles!", output.getMessage());
            assertNotNull(output.getData());

            verify(repository, times(1)).findAll();
            verify(repository, times(0)).getAllVehicleWithParameters(any(), any(), any());
        }

        @Test
        void shouldReturnAllVehiclesWithParameters() {
            var civic = new Vehicle(
                    1L, "civic", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            var city = new Vehicle(
                    1L, "city", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            var expectedFilteredVehiclesWithParameters = new ArrayList<>();
            expectedFilteredVehiclesWithParameters.add(civic);
            expectedFilteredVehiclesWithParameters.add(city);

            doReturn(List.of(civic, city)).when(repository)
                    .getAllVehicleWithParameters("honda", 2015, "white");

            var output = services.getAllVehicleWithParametersOrNot("honda", 2015, "white");

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to find all vehicles!", output.getMessage());
            assertEquals(expectedFilteredVehiclesWithParameters, output.getData());

            verify(repository, times(0)).findAll();
            verify(repository, times(1)).getAllVehicleWithParameters(
                    "honda", 2015, "white");
        }

        @Test
        void shouldReturnEmptyData() {
            doReturn(Collections.emptyList()).when(repository).getAllVehicleWithParameters(
                    "honda", 2015, "white");

            var output = services.getAllVehicleWithParametersOrNot("honda", 2015, "white");

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("the request was success but don't have register to return", output.getMessage());

            assertTrue(output.getData() instanceof List);

            List<Vehicle> dataList = (List<Vehicle>) output.getData();

            assertTrue(dataList.isEmpty());

            verify(repository, times(0)).findAll();
            verify(repository, times(1)).getAllVehicleWithParameters(
                    "honda", 2015, "white");
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).getAllVehicleWithParameters(
                    "honda", 2015, "white");

            var output = services.getAllVehicleWithParametersOrNot("honda", 2015, "white");

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(0)).findAll();
            verify(repository, times(1)).getAllVehicleWithParameters(
                    "honda", 2015, "white");
        }
    }
    
    @Nested
    class getVehicleById {

        @Test
        void shouldReturnVehicleByIdSuccessfully() {
            var vehicle = new Vehicle(
                    1L, "civic", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            doReturn(Optional.of(vehicle)).when(repository).findById(ArgumentMatchers.anyLong());

            var output = services.getVehicleById(1L);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to find vehicle by id informed!", output.getMessage());
            assertNotNull(output.getData());

            verify(repository, times(1)).findById(1L);
        }

        @Test
        void shouldThrowNoSuchElementException() {
            doReturn(Optional.empty()).when(repository).findById(ArgumentMatchers.anyLong());

            var output = services.getVehicleById(2L);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).findById(2L);
        }
    }

    @Nested
    class getCountUnsoldVehicles {

        @Test
        void shouldReturnCountVehiclesUnsoldSuccess() {
            Long expectedValue = 7L;

            doReturn(expectedValue).when(repository).getCountUnsoldVehicles();

            var output = services.getCountUnsoldVehicles();

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to count the unsold vehicles!", output.getMessage());
            assertNotNull(output.getData());
            assertEquals(7L, output.getData());

            verify(repository, times(1)).getCountUnsoldVehicles();
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).getCountUnsoldVehicles();

            var output = services.getCountUnsoldVehicles();

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).getCountUnsoldVehicles();
        }
    }

    @Nested
    class getCountVehiclesByDecade {

        @Test
        void shouldReturnCountVehiclesByDecadeSuccess() {
            doReturn(5L).when(repository).getCountVehiclesByDecade(2020, 2030);

            var output = services.getCountVehiclesByDecade(2025);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to count the decade vehicles!", output.getMessage());
            assertNotNull(output.getData());
            assertEquals(5L, output.getData());
            
            verify(repository, times(1)).getCountVehiclesByDecade(2020, 2030);
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).getCountVehiclesByDecade(2020, 2030);

            var output = services.getCountVehiclesByDecade(2025);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).getCountVehiclesByDecade(2020, 2030);
        }
    }

    @Nested
    class getCountByMake {

        @Test
        void shouldReturnCountVehiclesByMake() {
            doReturn(6L).when(repository).getCountByMake("honda");

            var output = services.getCountByMake("honda");

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to count the vehicles by the make!", output.getMessage());
            assertNotNull(output.getData());
            assertEquals(6L, output.getData());

            verify(repository, times(1)).getCountByMake("honda");
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).getCountByMake("honda");

            var output = services.getCountByMake("honda");

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).getCountByMake("honda");
        }
    }

    @Nested
    class getAllRegisteredVehiclesAtLastWeek {

        @Test
        void shouldReturnAllRegisteredVehiclesAtLastWeek() {
            List<Vehicle> mockList = new ArrayList<>();

            doReturn(mockList).when(repository).getAllRegisteredVehiclesAtLastWeek();

            var output = services.getAllRegisteredVehiclesAtLastWeek();

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to find all vehicles registered at last week!", output.getMessage());
            assertNotNull(output.getData());
            assertEquals(mockList, output.getData());

            verify(repository, times(1)).getAllRegisteredVehiclesAtLastWeek();
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).getAllRegisteredVehiclesAtLastWeek();

            var output = services.getAllRegisteredVehiclesAtLastWeek();

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).getAllRegisteredVehiclesAtLastWeek();
        }
    }

    @Nested
    class registerVehicle {

        @Test
        void shouldCreateVehicleWithSuccess() {
            var vehicle = new Vehicle(
                    1L, "civic", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            doReturn(vehicle).when(repository).save(vehicleArgumentCaptor.capture());

            var input = new RequestDTO(
                    "civic", "honda", 2015, "white", false);

            var output = services.registerVehicle(input);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to register the vehicle!", output.getMessage());
            assertNotNull(output.getData());

            verify(repository, times(1)).save(vehicleArgumentCaptor.getValue());
        }

        @Test
        void shouldReturnMessageInvalidMake() {
            var input = new RequestDTO(
                    "civic", "homda", 2015, "white", false);

            var output = services.registerVehicle(input);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("the make inserted isn't valid, fix the name and try again!", output.getMessage());
            assertNull(output.getData());

            verify(repository, never()).save(any());
        }


        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).save(any());

            var input = new RequestDTO(
                    "civic", "honda", 2015, "white", false);

            var output = services.registerVehicle(input);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).save(any());
        }
    }

    @Nested
    class updateAllVehicleInfo {

        @Test
        void shouldReturnUpdateAllVehicleInfoSuccess() {
            var vehicle = new Vehicle(
                    1L, "civic", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            doReturn(Optional.of(vehicle)).when(repository).findById(1L);
            doReturn(vehicle).when(repository).save(vehicleArgumentCaptor.capture());

            var input = new RequestDTO(
                    "civic", "honda", 2015, "white", false);

            var output = services.updateAllVehicleInfo(1L,input);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to update all info of the vehicle!", output.getMessage());
            assertNotNull(output.getData());

            verify(repository, times(1)).findById(1L);
            verify(repository, times(1)).save(any(Vehicle.class));
        }

        @Test
        void shouldReturnMessageInvalidMake() {
            var vehicle = new Vehicle(
                    1L, "civic", "homda", 2015, "white", false,
                    LocalDateTime.now(), null);

            doReturn(Optional.of(vehicle)).when(repository).findById(1L);

            var input = new RequestDTO(
                    "civic", "homda", 2015, "white", false);

            var output = services.updateAllVehicleInfo(1L, input);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("the make inserted isn't valid, fix the name and try again!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).findById(1L);
            verify(repository, times(0)).save(any(Vehicle.class));
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new NoSuchElementException()).when(repository).findById(1L);

            var input = new RequestDTO(
                    "civic", "honda", 2015, "white", false);

            var output = services.updateAllVehicleInfo(1L, input);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).findById(1L);
            verify(repository, times(0)).save(any(Vehicle.class));
        }
    }

    @Nested
    class updateSomeVehicleInfo {

        @Test
        void shouldReturnUpdateSomeVehicleInfoSuccess() {
            var vehicle = new Vehicle(
                    1L, "civic", "honda", 2015, "white", false,
                    LocalDateTime.now(), null);

            doReturn(Optional.of(vehicle)).when(repository).findById(1L);
            doReturn(vehicle).when(repository).save(vehicleArgumentCaptor.capture());

            var input = new RequestDTO(
                    "civic", "honda", 2015, "white", false);

            var output = services.updateSomeVehicleInfo(1L, input);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to update some info of the vehicle!", output.getMessage());
            assertNotNull(output.getData());

            verify(repository, times(1)).findById(1L);
            verify(repository, times(1)).save(any(Vehicle.class));
        }

        @Test
        void shouldReturnMessageInvalidMake() {
            var vehicle = new Vehicle(
                    1L, "civic", "homda", 2015, "white", false,
                    LocalDateTime.now(), null);

            doReturn(Optional.of(vehicle)).when(repository).findById(1L);

            var input = new RequestDTO(
                    "civic", "homda", 2015, "white", false);

            var output = services.updateSomeVehicleInfo(1L, input);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("the make inserted isn't valid, fix the name and try again!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).findById(1L);
            verify(repository, times(0)).save(any(Vehicle.class));
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new NoSuchElementException()).when(repository).findById(1L);

            var input = new RequestDTO(
                    "civic", "honda", 2015, "white", false);

            var output = services.updateSomeVehicleInfo(1L, input);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());
            assertNull(output.getData());

            verify(repository, times(1)).findById(1L);
            verify(repository, times(0)).save(any(Vehicle.class));
        }
    }

    @Nested
    class removeVehicle {

        @Test
        void shouldReturnRemoveVehicleSuccess() {
            var output = services.removeVehicle(1L);

            assertNotNull(output);
            assertTrue(output.isSuccess());
            assertEquals("success to remove the vehicle by id", output.getMessage());

            verify(repository, times(1)).deleteById(1L);
        }

        @Test
        void shouldThrowsExceptionWhenErrorOccurs() {
            doThrow(new RuntimeException()).when(repository).deleteById(1L);

            var output = services.removeVehicle(1L);

            assertNotNull(output);
            assertFalse(output.isSuccess());
            assertEquals("some error happened during the request!", output.getMessage());

            verify(repository, times(1)).deleteById(1L);
        }
    }
}