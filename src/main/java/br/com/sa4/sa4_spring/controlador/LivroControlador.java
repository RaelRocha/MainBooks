package br.com.sa4.sa4_spring.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.sa4.sa4_spring.excecao.LivroNaoEncontradoExcecao;
import br.com.sa4.sa4_spring.modelo.Livro;
import br.com.sa4.sa4_spring.servico.ILivroServico;

@Controller
@RequestMapping("/livro")
public class LivroControlador {

	@Autowired
	private ILivroServico service;

	@GetMapping("/")
	public String exibirPaginaInicial() {
		return "paginaInicio";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicao() {
		return "adicionarLivro";
	}

	@PostMapping("/salvar")
	public String salvarLivro(@ModelAttribute Livro livro, Model modelo) {
		service.salvarLivro(livro);
		Long codigo = service.salvarLivro(livro).getCodigo();
		String mensagem = "Livro com codigo: '" + codigo + "' salvo com sucesso!";
		modelo.addAttribute("message", mensagem);
		return "adicionarLivro";
	}

	@GetMapping("/listar")
	public String buscarTodosLivros(@RequestParam(value = "message", required = false) String mensagem, Model modelo) {
		List<Livro> livros = service.buscarTodosLivros();
		modelo.addAttribute("listagem", livros);
		modelo.addAttribute("message", mensagem);
		return "listarLivros";
	}

	@GetMapping("/editar")
	public String exibirFormularioEdicao(Model modelo, RedirectAttributes atributos, @RequestParam Long codigo) {
		String pagina = null;
		try {
			Livro livro = service.bucarLivroPorCodigo(codigo);
			modelo.addAttribute("livro", livro);
			pagina = "editarLivro";
		} catch (LivroNaoEncontradoExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
			pagina = "redirect:listar";
		}
		return pagina;
	}

	@PostMapping("/atualizar")
	public String atualizarLivro(@ModelAttribute Livro livro, RedirectAttributes atributos) {
		service.atualizarLivro(livro);
		Long codigo = livro.getCodigo();
		atributos.addAttribute("message", "Livro com codigo: '" + codigo + "' atualizado com sucesso!");
		return "redirect:listar";
	}

	@GetMapping("/deletar")
	public String deletarLivro(@RequestParam Long codigo, RedirectAttributes atributos) {
		try {
			service.deletarLivroPorCodigo(codigo);
			atributos.addAttribute("message", "Livro com codigo: '" + codigo + "' excluído com sucesso!");
		} catch (LivroNaoEncontradoExcecao e) {
			e.printStackTrace();
			atributos.addAttribute("message", e.getMessage());
		}
		return "redirect:listar";
	}
}