package com.demo.cliente.cliente.api.springdata.api;

import com.demo.cliente.cliente.api.springdata.dto.ClienteDto;
import com.demo.cliente.cliente.api.springdata.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cliente")
@Slf4j
public class ClienteApi {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/all")
    public List<ClienteDto> buscarClientes() {
        log.info("Busqueda de Clientes");
        return clienteService.obtenerClientes();
    }

    @GetMapping("/parameter")
    public ClienteDto buscarClientes(@RequestParam String identificacion) {
        log.info("Busqueda de Clientes");
        return clienteService.obtenerClientePorIdentificacion(identificacion);
    }

    @PostMapping
    public void crearCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Agregar: {}", clienteDto);
        clienteService.crearCliente(clienteDto);
    }

    @PutMapping
    public void actualizarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Actualizar: {}", clienteDto);
        clienteService.actualizarCliente(clienteDto);
    }

    @PutMapping(value = "/deasactivarCliente")
    public void deasactivarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Cliente a Desactivar: {}", clienteDto);
        clienteService.desactivarCliente(clienteDto.getId());
    }
}
