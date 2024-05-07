package com.icm.monitoreotemperaturaapi.services;

import com.icm.monitoreotemperaturaapi.models.CamionesModel;
import com.icm.monitoreotemperaturaapi.repositories.CamionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CamionesService {
    @Autowired
    private CamionesRepository camionesRepository;

    public List<CamionesModel> getAll() {
        return camionesRepository.findAll();
    }

    public Page<CamionesModel> getAll(Pageable pageable) {
        return camionesRepository.findAll(pageable);
    }

    public Optional<CamionesModel> getById(Long id) {
        return camionesRepository.findById(id);
    }

    public CamionesModel guardarCamion(CamionesModel camionesModel) {
        return camionesRepository.save(camionesModel);
    }

    public CamionesModel editarCamion(CamionesModel camionesModel, Long id) {
        Optional<CamionesModel> existing = camionesRepository.findById(id);
        if (existing.isPresent()) {
            CamionesModel data = existing.get();
            data.setPlaca(camionesModel.getPlaca());
            data.setTemperaturaActual(camionesModel.getTemperaturaActual());
            data.setTemperaturaMinima(camionesModel.getTemperaturaMinima());
            data.setTemperaturaMaxima(camionesModel.getTemperaturaMaxima());
            return camionesRepository.save(data);
        }
        return null;
    }

    public CamionesModel actualizarTemperatura(Long id, Double temperatura) {
        Optional<CamionesModel> existing = camionesRepository.findById(id);
        if (existing.isPresent()) {
            CamionesModel data = existing.get();
            data.setTemperaturaActual(temperatura);
            return camionesRepository.save(data); // Se debe guardar el modelo actualizado
        }
        return null;
    }

    public CamionesModel actualizarPosicion(Long id, BigDecimal longitud, BigDecimal latitud) {
        Optional<CamionesModel> existing = camionesRepository.findById(id);
        if (existing.isPresent()) {
            CamionesModel data = existing.get();
            data.setLongitud(longitud);
            data.setLatitud(latitud);
            return camionesRepository.save(data);
        }
        return null;
    }

    public void eliminarCamion(Long id) {
        camionesRepository.deleteById(id);
    }

}
