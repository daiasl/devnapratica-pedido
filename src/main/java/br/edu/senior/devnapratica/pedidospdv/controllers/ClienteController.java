package br.edu.senior.devnapratica.pedidospdv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.edu.senior.devnapratica.pedidospdv.domain.Cliente;
import br.edu.senior.devnapratica.pedidospdv.services.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {
	
	//injeção de dependência.
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET, path="/v1/clientes")
	public ResponseEntity<List<Cliente>> listar(){
		List<Cliente> clientes = clienteService.buscarTodos();
		return ResponseEntity.ok(clientes);		
	}
	//@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, path="/v1/clientes")
	@RequestMapping(method = RequestMethod.POST, path="/v1/clientes")
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
		Cliente clienteSalvo = clienteService.salvar(cliente);		
		return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
	}
	
	//? pode retornar clientes e fornecedores.
	@RequestMapping(method = RequestMethod.GET, path="/v1/clientes/{clienteId}")
	public ResponseEntity<? extends Cliente> buscar(@PathVariable("clienteId") Long idDoCliente){
		Optional<Cliente> clienteOpt = clienteService.buscar(idDoCliente);		
		
		if (clienteOpt.isPresent()) {
			return ResponseEntity.ok(clienteOpt.get());
		}		
		//Todos os buscar tem que retornar a mesma coisa, seguir um padrão. Não uma retorna NO_CONTENT e outro buscar retorna NOT_FOUND.
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path="/v1/clientes/{cliente.id}")
	public ResponseEntity<Cliente> alterar(@RequestBody Cliente cliente){
		Cliente clienteAlterado = clienteService.alterar(cliente);
		return new ResponseEntity<Cliente>(clienteAlterado, HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path="/v1/clientes/{clienteId}")
	public void remover(@PathVariable("clienteId") Long clienteId){
		clienteService.excluir(clienteId);
	}
	
	
}
