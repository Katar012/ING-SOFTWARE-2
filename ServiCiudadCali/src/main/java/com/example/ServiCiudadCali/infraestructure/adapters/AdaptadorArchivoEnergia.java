package com.example.ServiCiudadCali.infraestructure.adapters;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import com.example.ServiCiudadCali.infraestructure.repository.FacturaEnergiaRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdaptadorArchivoEnergia implements FacturaEnergiaRepository {

    private static final String ARCHIVO = "consumos_energia.txt";

    @Override
    public List<FacturaEnergia> obtenerFacturasDesdeArchivo() {
        List<FacturaEnergia> facturas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(ARCHIVO)))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                // Formato esperado: idCliente;periodo;consumoKwh;valorPagar
                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    String idCliente = partes[0];
                    String periodo = partes[1];
                    int consumoKwh = Integer.parseInt(partes[2]);
                    BigDecimal valorPagar = new BigDecimal(partes[3]);

                    FacturaEnergia factura = new FacturaEnergia(idCliente, periodo, consumoKwh, valorPagar);
                    facturas.add(factura);
                }
            }

        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Error al leer el archivo de energía: " + e.getMessage(), e);
        }

        return facturas;
    }
}
