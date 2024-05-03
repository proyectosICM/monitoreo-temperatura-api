package com.icm.monitoreotemperaturaapi.controllers;

import com.icm.monitoreotemperaturaapi.components.ErrorResponseBuilder;
import com.icm.monitoreotemperaturaapi.dto.ErrorResponse;
import com.icm.monitoreotemperaturaapi.models.CamionesModel;
import com.icm.monitoreotemperaturaapi.services.CamionesService;
import com.icm.monitoreotemperaturaapi.validators.CamionesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/camiones")
public class CamionesController {

    @Autowired
    private CamionesService camionesService;

    @Autowired
    private CamionesValidator camionesValidator;

    @Autowired
    private ErrorResponseBuilder errorResponseBuilder;

    @GetMapping
    public ResponseEntity<?> getAllCarriles() {
        return new ResponseEntity<>(camionesService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarrilById(@PathVariable Long id) {
        Optional<CamionesModel> existing = camionesService.getById(id);
        if (existing.isPresent()) {
            return ResponseEntity.ok(existing.get());
        } else {
            return errorResponseBuilder.buildNotFoundError("el camion");
        }
    }


    @PostMapping
    public ResponseEntity<?> guardarCamion(@RequestBody CamionesModel camion) {
        ResponseEntity<?> validationResponse = camionesValidator.validarDatos(camion);
        if (validationResponse != null) {
            return validationResponse;
        }

        CamionesModel nuevoCamion = camionesService.guardarCamion(camion);
        return new ResponseEntity<>(nuevoCamion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCamion(@RequestBody CamionesModel camion, @PathVariable Long id) {
        ResponseEntity<?> validationResponse = camionesValidator.validarDatos(camion);
        if (validationResponse != null) {
            return validationResponse;
        }

        CamionesModel camionEditado = camionesService.editarCamion(camion, id);

        if (camionEditado != null) {
            return new ResponseEntity<>(camionEditado, HttpStatus.OK);
        } else {
            return errorResponseBuilder.buildNotFoundError("el carril");
        }
    }

    @PatchMapping("/temperatura/{id}")
    public ResponseEntity<?> actualizarTemperatura(@PathVariable Long id, @RequestParam Double temperatura) {
        CamionesModel camionActualizado = camionesService.actualizarTemperatura(id, temperatura);
        return camionActualizado != null ?
                ResponseEntity.ok(camionActualizado) :
                errorResponseBuilder.buildNotFoundError("camión");
    }

    @PatchMapping("/posicion/{id}")
    public ResponseEntity<?> actualizarPosicion(@PathVariable Long id, @RequestParam BigDecimal longitud, @RequestParam BigDecimal latitud) {
        CamionesModel camionActualizado = camionesService.actualizarPosicion(id, longitud, latitud);
        return camionActualizado != null ?
                ResponseEntity.ok(camionActualizado) :
                errorResponseBuilder.buildNotFoundError("camión");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCamion(@PathVariable Long id) {
        Optional<CamionesModel> carril = camionesService.getById(id);
        if (!carril.isPresent()) {
            return errorResponseBuilder.buildNotFoundError("el carril");
        }

        camionesService.eliminarCamion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
