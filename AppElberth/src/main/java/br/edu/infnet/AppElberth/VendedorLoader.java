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

import br.edu.infnet.AppElberth.model.domain.Vendedor;
import br.edu.infnet.AppElberth.model.service.VendedorService;

@Order(2)
@Component
public class VendedorLoader implements ApplicationRunner {
	
	@Autowired
	private VendedorService vendedorService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			FileReader arquivo = new FileReader("files/vendedores.csv");
			BufferedReader leitura = new BufferedReader(arquivo);
			
			String linha = leitura.readLine();
			
			String[] campos = null;

			while(linha != null) {
				campos = linha.split(",");
				
				Vendedor vendedor = new Vendedor();
				vendedor.nome = campos[0];
				vendedor.email = campos[1];
				vendedor.telefone = campos[2];
				vendedor.cpf = campos[3];
				
				vendedorService.incluir(vendedor);

				linha = leitura.readLine();			
			}
			
			System.out.println("#VENDEDOR");
			for(Vendedor item : vendedorService.obterLista()) {
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
