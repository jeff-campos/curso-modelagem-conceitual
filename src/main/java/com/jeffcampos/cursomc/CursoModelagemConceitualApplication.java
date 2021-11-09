package com.jeffcampos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeffcampos.cursomc.domain.Categoria;
import com.jeffcampos.cursomc.domain.Cidade;
import com.jeffcampos.cursomc.domain.Cliente;
import com.jeffcampos.cursomc.domain.Endereco;
import com.jeffcampos.cursomc.domain.Estado;
import com.jeffcampos.cursomc.domain.Produto;
import com.jeffcampos.cursomc.domain.enums.TipoCliente;
import com.jeffcampos.cursomc.repositories.CategoriaRepository;
import com.jeffcampos.cursomc.repositories.CidadeRepository;
import com.jeffcampos.cursomc.repositories.ClienteRepository;
import com.jeffcampos.cursomc.repositories.EnderecoRepository;
import com.jeffcampos.cursomc.repositories.EstadoRepository;
import com.jeffcampos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoModelagemConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritótio");

		Produto p1 = new Produto(null, "Computador", 200.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Jeff Campos", "contato@jeffcampos.net", "23069857825",
				TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "9493933123"));

		Endereco e1 = new Endereco(null, "Rua Nilza", "480", "Apto 303", "Vila Esperança", "03651120", cli1, c3);
		Endereco e2 = new Endereco(null, "Avenida dos Itabarés", "4562", null, "Milenio", "09372100", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
