package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Cliente;
import com.example.demo.repositorio.ClienteRepository;

@RestController
@RequestMapping("/clientes") // Ruta base para las operaciones relacionadas con clientes
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Crear un nuevo cliente
    @PostMapping("/")
    public void crearCliente(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
    }

    // Actualizar un cliente por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

    // Borrar un cliente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable Integer id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Consultar todos los clientes
    @GetMapping("/")
    public List<Cliente> darClientes() {
        return clienteRepository.findAll();
    }

    // Consultar un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> darCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar la llegada de un cliente al hotel por fecha de entrada
    @GetMapping("/llegada")
    public List<Cliente> consultarLlegadaClienteAlHotel(@RequestParam("fechaEntrada") String fechaEntrada) {
        return clienteRepository.consultarLlegadaClienteAlHotel(fechaEntrada);
    }
    
    // Consultar la salida de un cliente al hotel por fecha de salida
    @GetMapping("/salida")
    public List<Cliente> consultarSalidaClienteAlHotel(@RequestParam("fechaSalida") String fechaSalida) {
        return clienteRepository.consultarSalidaClienteAlHotel(fechaSalida);
    }

    // Consultar los buenos clientes según los criterios especificados
    @GetMapping("/clientes/buenos")
    public List<Cliente> consultarBuenosClientesUltimoAnio() {
        return clienteRepository.consultarBuenosClientesUltimoAnio();
    }
}
