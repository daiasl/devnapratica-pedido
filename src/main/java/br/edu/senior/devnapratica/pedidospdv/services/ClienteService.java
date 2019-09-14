package br.edu.senior.devnapratica.pedidospdv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senior.devnapratica.pedidospdv.dao.ClienteDAO;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDAO clienteDAO;

	public List<Cliente> buscarTodos(){
		return clienteDAO.buscarTodos();
	}
	//optional surgiu apartir do java 8. buscar pode ou não retornar o cliente. se eu mandar um ID que não tem...
	public Optional<Cliente> buscar(Long clienteId){
		return clienteDAO.buscar(clienteId);
	}
	public Cliente salvar(Cliente cliente) {		
		boolean temClienteComMesmoEmail = clienteDAO.buscarTodos()
				.stream()
				.anyMatch(outroCliente -> outroCliente.getEmail().equals(cliente.getEmail()));			
		if (temClienteComMesmoEmail) {					
			throw new IllegalArgumentException("Já existe um cliente com este e-mail.");
		}		
		return clienteDAO.salvar(cliente);
	}
	public Cliente alterar(Cliente cliente) {
		return clienteDAO.alterar(cliente);
	}
	public void excluir(Long clienteId) {
		clienteDAO.excluir(clienteId);
	}
	public static void main(String[] args) {
			
		
	}
	
}



