package com.icm.monitoreotemperaturaapi.repositories;

import com.icm.monitoreotemperaturaapi.models.CamionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionesRepository extends JpaRepository<CamionesModel, Long> {
}
