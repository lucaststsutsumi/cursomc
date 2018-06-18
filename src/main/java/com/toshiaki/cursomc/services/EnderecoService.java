package com.toshiaki.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toshiaki.cursomc.domain.Endereco;
import com.toshiaki.cursomc.repositories.EnderecoRepository;
import com.toshiaki.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"O endereço com id " + id + "não foi encontrado em nossa base de dados"));
	}
}
