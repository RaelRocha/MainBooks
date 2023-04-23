package br.com.sa4.sa4_spring.servico.implementacao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.sa4.sa4_spring.excecao.LivroNaoEncontradoExcecao;
import br.com.sa4.sa4_spring.modelo.Livro;
import br.com.sa4.sa4_spring.repositorio.LivroRepositorio;
import br.com.sa4.sa4_spring.servico.ILivroServico;
 
@Service
public class LivroServicoImpl implements ILivroServico {
 
	@Autowired
	private LivroRepositorio repositorio;
 
	@Override
	public Livro salvarLivro(Livro livro) {
		return repositorio.save(livro);
	}
 
	@Override
	public List<Livro> buscarTodosLivros() {
		return repositorio.findAll();
	}
 
	@Override
	public Livro bucarLivroPorCodigo(Long codigo) {
		Optional<Livro> opcional = repositorio.findById(codigo);
		if (opcional.isPresent()) {
			return opcional.get();
		} else {
			throw new LivroNaoEncontradoExcecao("Livro com codigo: " + codigo + " não encontrado.");
		}
	}
 
	@Override
	public void deletarLivroPorCodigo(Long codigo) {
		repositorio.delete(bucarLivroPorCodigo(codigo));
	}
 
	@Override
	public void atualizarLivro(Livro invoice) {
		repositorio.save(invoice);
	}
}