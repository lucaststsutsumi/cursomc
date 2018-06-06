package com.toshiaki.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toshiaki.cursomc.domain.Cliente;
import com.toshiaki.cursomc.repositories.ClienteRepository;
import com.toshiaki.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		System.out.println(cliente);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"O objeto com o Id: " + id + " não foi encontrado em nossa base de dados"));
	}
}
