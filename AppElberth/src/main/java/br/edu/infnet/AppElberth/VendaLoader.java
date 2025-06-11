package br.edu.infnet.AppElberth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.AppElberth.model.domain.Cliente;
import br.edu.infnet.AppElberth.model.domain.Produto;
import br.edu.infnet.AppElberth.model.domain.Venda;
import br.edu.infnet.AppElberth.model.service.ClienteService;
import br.edu.infnet.AppElberth.model.service.ProdutoService;
import br.edu.infnet.AppElberth.model.service.VendaService;

@Component
public class VendaLoader implements ApplicationRunner {
	
	@Autowired
	private VendaService vendaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	
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
				
				venda.setData(LocalDateTime.parse(campos[0], formatter));
				venda.setQuantidade(Integer.valueOf(campos[1]));
				
				String cpfCliente = campos[2].trim();
				Cliente cliente = clienteService.obterPorCPF(cpfCliente);
				if(cliente == null) {
					System.out.println("Cliente não encontrado com o CPF: " + cpfCliente);
				} else {
					venda.setCliente(cliente);
				}
				
				String[] codigosProdutos = campos[3].split("\\^");
				
				List<Produto> produtos = new ArrayList<Produto>();

				for(String codProd : codigosProdutos) {
					Produto produto = produtoService.obterPorCodigo(Integer.valueOf(codProd));		
					if(produto == null) {
						System.out.println("Produto não encontrado com o código: " + codProd);
					} else {
						produtos.add(produto);
					}
				}
				
				venda.setProdutos(produtos);

				if(venda.getCliente() != null && !venda.getProdutos().isEmpty()) {
					vendaService.incluir(venda);
				} else {
					System.out.println("Venda não realizada!");
				}
				
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
