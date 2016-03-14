package modulo06;

public class Modulo06 {
	
	public static void main(String[] args) {		
		
		// livros do estoque
		Livro livro1 = new Livro("Fisica", "Mecanica Classica");
		Livro livro2 = new Livro("Quimica", "Organica");
		Livro livro3 = new Livro("Matematica", "Geometria Aplicada");
		Livro livro4 = new Livro("Geografia", "Estudo das Rochas");
		
		
		ControleEstoque controlEstoq = new ControleEstoque();
		
		controlEstoq.adicionarItem(livro1, 10);
		controlEstoq.adicionarItem(livro2, 5);
		controlEstoq.adicionarItem(livro3, 3);
		controlEstoq.adicionarItem(livro4, 1);
		
		//adicionar elementos repetidos..
		controlEstoq.adicionarItem(livro1, 2);
		
		//criando e adicionando elementos repetidos
		Livro livroExtra = new Livro("Quimica", "Organica");
		controlEstoq.adicionarItem(livroExtra, 5);
		
		
		boolean testeRemover;
				
		Livro livroAux = new Livro("Fisica", "Mecanica Classica");
		
		testeRemover = controlEstoq.removerItem(livroAux, 4);		
		System.out.println(testeRemover);
		
		livroAux = new Livro("Quimica", "Organica");
		testeRemover = controlEstoq.removerItem(livroAux, 3);		
		System.out.println(testeRemover);
		
		livroAux = new Livro("Matematica", "Geometria Aplicada");
		testeRemover = controlEstoq.removerItem(livroAux, 2);		
		System.out.println(testeRemover);
		
		livroAux = new Livro("Geografia", "Estudo das Rochas");
		testeRemover = controlEstoq.removerItem(livroAux, 1);		
		System.out.println(testeRemover);
		
		
		ItemEstoque item1 = controlEstoq.buscarItem("Fisica", "Mecanica Classica");
		System.out.println(item1.getLivro().getTitulo() +" "+ item1.getQuantidade());
		
		ItemEstoque item2 = controlEstoq.buscarItem("Quimica", "Organica");
		System.out.println(item2.getLivro().getTitulo() +" "+ item2.getQuantidade());
		
		ItemEstoque item3 = controlEstoq.buscarItem("Matematica", "Geometria Aplicada");
		System.out.println(item3.getLivro().getTitulo() +" "+ item3.getQuantidade());
		
		ItemEstoque item4 = controlEstoq.buscarItem("Geografia", "Estudo das Rochas");
		System.out.println(item4.getLivro().getTitulo() +" "+ item4.getQuantidade());
		
	}
	
	
}
