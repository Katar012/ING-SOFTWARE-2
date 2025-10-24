package com.example.ServiCiudadCali.infrastructure.adapter;

import com.example.ServiCiudadCali.domain.ports.outs.AcueductoPort;
import com.example.ServiCiudadCali.domain.ports.outs.ClientePort;
import com.example.ServiCiudadCali.domain.ports.outs.EnergiaPort;
import com.example.ServiCiudadCali.domain.useCase.ConsultarFacturasClienteUseCaseImpl;
import org.springframework.stereotype.Service;

@Service
public class ConsultarFacturasClienteService extends ConsultarFacturasClienteUseCaseImpl {
    public ConsultarFacturasClienteService(AcueductoPort acueductoPort, EnergiaPort energiaPort, ClientePort clientePort) {
        super(acueductoPort, energiaPort, clientePort);
    }
}
