package com.example.ServiCiudadCali.domain.useCase;

import com.example.ServiCiudadCali.application.dto.DeudaConsolidadaDTO;
import com.example.ServiCiudadCali.domain.exception.ResourceNotFoundException;
import com.example.ServiCiudadCali.domain.model.FacturaAcueducto;
import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import com.example.ServiCiudadCali.domain.ports.in.ConsultarFacturasClienteUseCase;
import com.example.ServiCiudadCali.domain.ports.outs.AcueductoPort;
import com.example.ServiCiudadCali.domain.ports.outs.EnergiaPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ConsultarFacturasClienteUseCaseImpl implements ConsultarFacturasClienteUseCase {

    //Probemos la inyección sin autowired a ver que tal

    private final AcueductoPort acueductoPort;
    private final EnergiaPort energiaPort;

    public ConsultarFacturasClienteUseCaseImpl(AcueductoPort acueductoPort, EnergiaPort energiaPort) {
        this.acueductoPort = acueductoPort;
        this.energiaPort = energiaPort;
    }

    @Override
    public DeudaConsolidadaDTO consultarPorCliente(String clienteId){
        if (clienteId == null || clienteId.isBlank()) {
            throw new IllegalArgumentException("clienteId es necesario");
        }

        Optional<FacturaAcueducto> facturaAcueductoExiste = acueductoPort.obtenerPorCliente(clienteId);
        Optional<FacturaEnergia> facturaEnergiaExiste = energiaPort.obtenerPorCliente(clienteId);
        if (!facturaAcueductoExiste.isPresent()){
            throw new ResourceNotFoundException("El id: " + clienteId + " no corresponde a la factura de acueducto de ningun cliente");
        }
        if (!facturaEnergiaExiste.isPresent()){
            throw new ResourceNotFoundException("El id: " + clienteId + " no corresponde a la factura de energia de ningun cliente");
        }

        FacturaAcueducto facturaActu = facturaAcueductoExiste.get();
        FacturaEnergia facturaEner = facturaEnergiaExiste.get();


        DeudaConsolidadaDTO.FacturaAcueductoDTO faDto = null;
        DeudaConsolidadaDTO.FacturaEnergiaDTO feDto = null;

        faDto = DeudaConsolidadaDTO.FacturaAcueductoDTO.builder().periodo(facturaActu.getPeriodo()).consumo(facturaActu.getConsumoM3()).valorPagar(facturaActu.getValorPagar()).build();
        feDto = DeudaConsolidadaDTO.FacturaEnergiaDTO.builder().periodo(facturaEner.getPeriodo()).consumo(facturaEner.getConsumokwh()).valorPagar(facturaEner.getValorPagar()).build();

        DeudaConsolidadaDTO.ResumenDeudaDTO resumen = null;

        resumen = DeudaConsolidadaDTO.ResumenDeudaDTO.builder().facturaAcueducto(faDto).facturaEnergia(feDto).build();

        //Desde aqui construimos la deuda consolidada final
        DeudaConsolidadaDTO deuda = null;

        BigDecimal suma = BigDecimal.ZERO;
        suma = suma.add(faDto.getValorPagar());
        suma = suma.add(feDto.getValorPagar());
        LocalDateTime fechaAhora = LocalDateTime.now();

        deuda = DeudaConsolidadaDTO.builder().clienteId(clienteId).nombreCliente("En proceso (aqui deberia decir el nombre del cliente)").fechaConsulta(fechaAhora).resumenDeuda(resumen).totalAPagar(suma).build();

        return deuda;


    }


}
