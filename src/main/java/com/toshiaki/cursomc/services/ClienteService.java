package com.toshiaki.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.toshiaki.cursomc.domain.Cliente;
import com.toshiaki.cursomc.dto.ClienteDTO;
import com.toshiaki.cursomc.repositories.ClienteRepository;
import com.toshiaki.cursomc.services.exceptions.DataIntegrityException;
import com.toshiaki.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		System.out.println(cliente);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"O objeto com o Id: " + id + " não foi encontrado em nossa base de dados"));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		try{
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir Cliente que possua vinculos");
		}
	}
	
	public List<Cliente> findAll(){
		List<Cliente> list = repo.findAll();
		return list;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(),objDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
	}
}
