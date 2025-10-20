package com.example.ServiCiudadCali.infrastructure.repository;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import com.example.ServiCiudadCali.domain.ports.outs.EnergiaPort;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Optional;

@Component
public class FacturaEnergiaRepositoryAdapter implements EnergiaPort {

    private static final String ARCHIVO_ENERGIA = "consumos_energia.txt";

    @Override
    public Optional<FacturaEnergia> obtenerPorCliente(String clienteId) {
        var inputStream = getClass().getClassLoader().getResourceAsStream(ARCHIVO_ENERGIA);
        if (inputStream == null) {
            return Optional.empty();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String linea;
        FacturaEnergia ultimaFactura = null;

        try {
            while ((linea = reader.readLine()) != null) {
                // Formato: id_cliente(10), periodo(6), consumo_kwh(8), valor_pagar(12)
                String id = linea.substring(0, 10);
                String periodo = linea.substring(10, 16);
                int consumoKwh = Integer.parseInt(linea.substring(16, 24).trim());
                BigDecimal valorPagar = new BigDecimal(linea.substring(24).trim());

                if (id.equals(clienteId)) {
                    ultimaFactura = new FacturaEnergia(id, periodo, consumoKwh, valorPagar);
                }
            }
        } catch (Exception ignored) {
            // no lanzamos excepcion
        }

        return Optional.ofNullable(ultimaFactura);
    }
}
