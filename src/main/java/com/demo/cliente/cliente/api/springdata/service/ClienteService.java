package com.demo.cliente.cliente.api.springdata.service;

import com.demo.cliente.cliente.api.springdata.dto.ClienteDto;
import com.demo.cliente.cliente.api.springdata.model.Cliente;
import com.demo.cliente.cliente.api.springdata.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    private ClienteDto fromClienteToDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    public List<ClienteDto> obtenerClientes() {
        return clienteRepository.findAll()
                .stream().map(this::fromClienteToDto).collect(Collectors.toList());
    }

    public ClienteDto obtenerClientePorIdentificacion(String identificacion) {
        Cliente cliente = clienteRepository.findClienteByIdentificacion(identificacion);

        return fromClienteToDto(cliente);
    }

    public void desactivarCliente(int idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(
                        () -> {
                            throw new RuntimeException("Cliente No Existe");
                        });

        cliente.setEstado(false);
    }

    public void crearCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        cliente.setDireccionDomicilio(clienteDto.getDireccionDomicilio());
        cliente.setTelefonoContacto(clienteDto.getTelefonoContacto());
        cliente.setEstado(true);
        clienteRepository.save(cliente);
    }

    public void actualizarCliente(ClienteDto clienteDto) {

        Cliente cliente = clienteRepository.findClienteById(clienteDto.getId());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccionDomicilio(clienteDto.getDireccionDomicilio());
        cliente.setTelefonoContacto(clienteDto.getTelefonoContacto());
        clienteRepository.save(cliente);

    }
}
