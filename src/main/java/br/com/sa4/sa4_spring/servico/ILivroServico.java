package br.com.sa4.sa4_spring.servico;

import java.util.List;
import br.com.sa4.sa4_spring.modelo.Livro;

public interface ILivroServico {

	public Livro salvarLivro(Livro livro);

	public List<Livro> buscarTodosLivros();

	public Livro bucarLivroPorCodigo(Long codigo);

	public void deletarLivroPorCodigo(Long codigo);

	public void atualizarLivro(Livro livro);

}