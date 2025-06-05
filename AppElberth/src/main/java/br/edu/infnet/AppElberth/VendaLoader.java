package br.edu.infnet.AppElberth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.AppElberth.model.domain.Venda;
import br.edu.infnet.AppElberth.model.service.VendaService;

@Component
public class VendaLoader implements ApplicationRunner {
	
	@Autowired
	private VendaService vendaService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			FileReader arquivo = new FileReader("files/vendas.csv");
			BufferedReader leitura = new BufferedReader(arquivo);
			
			String linha = leitura.readLine();
			
			String[] campos = null;

			while(linha != null) {
				campos = linha.split(",");
					        
				Venda venda = new Venda();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				
				venda.data = LocalDateTime.parse(campos[0], formatter);
				venda.quantidade = Integer.valueOf(campos[1]);

				vendaService.incluir(venda);

				linha = leitura.readLine();			
			}
			
			System.out.println("#VENDA");
			for(Venda item : vendaService.obterLista()) {
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
