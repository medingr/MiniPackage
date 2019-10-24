package com.thoughtworks.miniPackage.service;

import com.thoughtworks.miniPackage.model.MiniPackage;
import com.thoughtworks.miniPackage.repository.MiniPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MiniPackageService {

    @Autowired
    MiniPackageRepository miniPackageRepository;


    public MiniPackage addPackage(MiniPackage pack) {
        MiniPackage miniPackageToAdd = new MiniPackage();
        String localDate = LocalDate.now().toString();
        miniPackageToAdd.setClientName(pack.getClientName());
        miniPackageToAdd.setBookingTime(localDate);
        miniPackageToAdd.setStatus("Reservation");
        miniPackageToAdd.setTelephone(pack.getTelephone());
        return miniPackageRepository.save(miniPackageToAdd);

    }

    public Iterable<MiniPackage> findAll(PageRequest name) {
        return miniPackageRepository.findAll(name);
    }

}
