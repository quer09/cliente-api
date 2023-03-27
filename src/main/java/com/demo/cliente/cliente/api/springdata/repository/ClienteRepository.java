package com.demo.cliente.cliente.api.springdata.repository;

import com.demo.cliente.cliente.api.springdata.dto.ClienteDto;
import com.demo.cliente.cliente.api.springdata.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    List<Cliente> findAll();

    Cliente findClienteById(int id);

    Cliente findClienteByIdentificacion(String identificacion);
}
