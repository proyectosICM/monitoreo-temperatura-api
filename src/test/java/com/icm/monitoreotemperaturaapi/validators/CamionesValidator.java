package com.icm.monitoreotemperaturaapi.validators;

import com.icm.monitoreotemperaturaapi.dto.ErrorResponse;
import com.icm.monitoreotemperaturaapi.models.CamionesModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CamionesValidator {
    public ResponseEntity<?> validarDatos(CamionesModel camionesModel) {
        List<String> errores = new ArrayList<>();

        if (camionesModel.getPlaca() == null) {
            errores.add("La placa es obligatoria");
        }

        if (camionesModel.getTemperaturaActual() == null) {
            errores.add("La temperatura actual es obligatoria");
        }

        if (camionesModel.getTemperaturaMinima() == null) {
            errores.add("La temperatura mínima es obligatoria");
        }

        if (camionesModel.getTemperaturaMaxima() == null) {
            errores.add("La temperatura máxima es obligatoria");
        }

        if (camionesModel.getLongitud() == null) {
            errores.add("La longitud es obligatoria");
        }

        if (camionesModel.getLatitud() == null) {
            errores.add("La latitud es obligatoria");
        }

        if (!errores.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse("Solicitud incorrecta", String.join("; ", errores));
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
