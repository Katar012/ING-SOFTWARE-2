package com.example.ServiCiudadCali.infrastructure.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import com.example.ServiCiudadCali.domain.ports.outs.EnergiaPort;
import org.springframework.stereotype.Component;

@Component
//Pide abstraccion de EnergiaPort
public abstract class FacturaEnergiaRepositoryAdapter implements EnergiaPort {

    private final String rutaArchivo = "data/consumos_energia.txt";

    @Override
    public List<FacturaEnergia> obtenerPorCliente(String clienteId) {
        List<FacturaEnergia> facturas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // Supongamos que el formato es: clienteId;mes;consumo;valor
                if (partes[0].equals(clienteId)) {
                    FacturaEnergia factura = new FacturaEnergia(
                        partes[0],
                        partes[1],
                        Double.parseDouble(partes[2]),
                        Double.parseDouble(partes[3])
                    );
                    facturas.add(factura);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return facturas;
    }
}
