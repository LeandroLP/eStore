package br.com.fiap.nac.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import br.com.fiap.nac.dao.AutorDAO;
import br.com.fiap.nac.dao.CategoriaDAO;
import br.com.fiap.nac.dao.EditoraDAO;
import br.com.fiap.nac.dao.GeneroDAO;
import br.com.fiap.nac.dao.LivroDAO;
import br.com.fiap.nac.to.Categoria;
import br.com.fiap.nac.to.Livro;

@ManagedBean
@SessionScoped
public class LivroBean {

	private Livro livro;
	private LivroDAO livroDAO;
	private List<Livro> listLivro;
	private String mensagem;
	private Part file;

	public Livro getLivro() {
		return livro;
	}

	public void setAutor(Livro livro) {
		this.livro = livro;
	}

	public LivroDAO getLivroDAO() {
		return this.livroDAO;
	}

	public void setLivroDAO(LivroDAO livroDAO) {
		this.livroDAO = livroDAO;
	}

	public List<Livro> getListLivro() {
		return listLivro;
	}

	public void setListLivro(List<Livro> listLivro) {
		this.listLivro = listLivro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	@PostConstruct
	public void init() {
		livroDAO = new LivroDAO();

		limparCarregar();
	}

	// Persistencia
	public String salvar() {
		FacesMessage message = null;
		try {			
			if(livro.getLivroId() != null){
				if(file != null){
					livro.setImagem(file.getInputStream());				
				}
				
				if (livroDAO.update(livro)) {
					message = new FacesMessage("Livro alterado com sucesso!");
				}
			} else {
				if(file == null){
					message = new FacesMessage("Favor selecionar uma imagem!");
				} else {
					livro.setImagem(file.getInputStream());
					
					if (livroDAO.save(livro) != null) {
						message = new FacesMessage("Livro cadastrado com sucesso!");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();

		return null;
	}

	public String alterar(Livro livro) {
		this.livro = livro;
		
		FacesMessage message = null;
		try {
			AutorDAO autorDAO = new AutorDAO();
			livro.setListAutor(autorDAO.getAll());

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			livro.setListCategoria(categoriaDAO.getAll());

			EditoraDAO editoraDAO = new EditoraDAO();
			livro.setListEditora(editoraDAO.getAll());

			GeneroDAO generoDAO = new GeneroDAO();
			livro.setListGenero(generoDAO.getAll());
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}
		
		if(message != null){
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return null;
	}

	public String excluir(Livro livro) {
		FacesMessage message = null;
		try {
			if (livroDAO.delete(livro)) {
				message = new FacesMessage("Livro excluido com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}
		
		if(message != null){
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		limparCarregar();
		return null;
	}

	public String novo() {
		limparCarregar();

		return null;
	}

	private void limparCarregar() {
		livro = new Livro();		

		FacesMessage message = null;
		try {
			listLivro = livroDAO.getAll();
			
			AutorDAO autorDAO = new AutorDAO();
			livro.setListAutor(autorDAO.getAll());

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			livro.setListCategoria(categoriaDAO.getAll());

			EditoraDAO editoraDAO = new EditoraDAO();
			livro.setListEditora(editoraDAO.getAll());

			GeneroDAO generoDAO = new GeneroDAO();
			livro.setListGenero(generoDAO.getAll());
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		if(message != null){
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public String filtrarPorCategoria(Categoria categoria){
		FacesMessage message = null;
		try {
			listLivro = livroDAO.getByCategoria(categoria);
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}
		
		if(message != null){
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
    	return null;
    }
	
	public String carregarTodos(){
		FacesMessage message = null;
		try {
			listLivro = livroDAO.getAll();
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}
		
		if(message != null){
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
    	return null;
    }
}
