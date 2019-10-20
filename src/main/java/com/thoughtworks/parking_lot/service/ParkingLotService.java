package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ParkingLotService {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ParkingLot findByName(String parkingLotName) {
        return parkingLotRepository.findByname(parkingLotName);
    }

    public Boolean saveParkingLot(ParkingLot parkingLot) {
        ParkingLot findParkingLot = parkingLotRepository.findByname(parkingLot.getName());
        if ( findParkingLot == null) {
            parkingLotRepository.save(parkingLot);
            return true;
        } return false;
    }

    public Boolean deleteParkingLot(String parkingLotName) {
        ParkingLot parkingLotToDelete = parkingLotRepository.findByname(parkingLotName);
        if ( parkingLotToDelete != null) {
            parkingLotRepository.delete(parkingLotToDelete);
            return true;
        } return false;
    }

    public Iterable<ParkingLot> findAll(PageRequest name) {
        return parkingLotRepository.findAll(name);
    }

    public ParkingLot updateParkingLotCapacity(String parkingLotName, ParkingLot newParkingLot) {
        ParkingLot oldParkingLot = parkingLotRepository.findByname(parkingLotName);
        if(!isNull(oldParkingLot)){
            oldParkingLot.setCapacity(newParkingLot.getCapacity());
            return oldParkingLot;
        }
        return null;
    }
}