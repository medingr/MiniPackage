package com.thoughtworks.miniPackage.repository;
import com.thoughtworks.miniPackage.model.MiniPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiniPackageRepository extends JpaRepository<MiniPackage, Long> {

}
