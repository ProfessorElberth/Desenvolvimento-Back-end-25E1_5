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

import br.edu.infnet.AppElberth.model.domain.Produto;
import br.edu.infnet.AppElberth.model.domain.Vendedor;
import br.edu.infnet.AppElberth.model.service.ProdutoService;
import br.edu.infnet.AppElberth.model.service.VendedorService;

@Order(3)
@Component
public class ProdutoLoader implements ApplicationRunner {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private VendedorService vendedorService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			FileReader arquivo = new FileReader("files/produtos.csv");
			BufferedReader leitura = new BufferedReader(arquivo);
			
			String linha = leitura.readLine();
			
			String[] campos = null;

			while(linha != null) {
				campos = linha.split(",");

				Produto produto = new Produto();
				produto.nome = campos[0];
				produto.tipo = campos[1];
				produto.categoria = campos[2];
				produto.descricao = campos[3];
				produto.preco = Float.valueOf(campos[4]);
				produto.estoque = Integer.valueOf(campos[5]);
				
				String cpf = campos[6].trim();
				Vendedor vendedor = vendedorService.obterPorCPF(cpf);
				
				if(vendedor == null) {
					System.err.println("Vendedor não encontrado com o CPF " + cpf);
				} else {
					produto.vendedor = vendedor;
					produtoService.incluir(produto);
				}

				linha = leitura.readLine();			
			}
			
			System.out.println("#PRODUTO");
			for(Produto item : produtoService.obterLista()) {
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