package com.toshiaki.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.toshiaki.cursomc.domain.Categoria;
import com.toshiaki.cursomc.repositories.CategoriaRepository;
import com.toshiaki.cursomc.services.exceptions.DataIntegrityException;
import com.toshiaki.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id:" + id + " Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		Categoria obj = repo.save(categoria);
		return obj;
	}
	
	public void delete(Integer id) {
		try{
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Categoria que possua produtos vinculados");
		}
	}
	
	
	public List<Categoria> findAll(){
		List<Categoria> list = repo.findAll();
		return list;
	}
}
