package br.edu.infnet.AppElberth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.AppElberth.model.domain.Cliente;
import br.edu.infnet.AppElberth.model.service.ClienteService;

@Order(1)
@Component
public class ClienteLoader implements ApplicationRunner {
	
	@Autowired
	private ClienteService clienteService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			FileReader arquivo = new FileReader("files/clientes.csv");
			BufferedReader leitura = new BufferedReader(arquivo);
			
			String linha = leitura.readLine();
			
			String[] campos = null;

			while(linha != null) {
				campos = linha.split(",");
				
				Cliente cliente = new Cliente();
				cliente.nome = campos[0];
				cliente.email = campos[1];
				cliente.telefone = campos[2];

				clienteService.incluir(cliente);
				
				linha = leitura.readLine();			
			}

			System.out.println("#CLIENTE");
			for(Cliente item : clienteService.obterLista()) {
				System.out.println(item);				
			}
			
			leitura.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não identificado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Impossível abrir/fechar o arquivo!");
			e.printStackTrace();
		}		
	}
}