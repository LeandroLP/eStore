package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import br.com.fiap.nac.dao.AutorDAO;
import br.com.fiap.nac.dao.CategoriaDAO;
import br.com.fiap.nac.dao.EditoraDAO;
import br.com.fiap.nac.dao.GeneroDAO;
import br.com.fiap.nac.dao.LivroDAO;
import br.com.fiap.nac.to.Autor;
import br.com.fiap.nac.to.Livro;

@ManagedBean
@SessionScoped
public class LivroBean {

	private Livro livro;
	// private LazyDataModel<Autor> listAutor;
	private LivroDAO livroDAO;
	private List<Livro> listLivro;
	private String mensagem;
	private UploadedFile file;

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

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	@PostConstruct
	public void init() {

		livroDAO = new LivroDAO();
		limparCarregar();

	}

	// Persistencia
	public String salvar() {
		
		livro.setImagem(file.getContents());
		
		FacesMessage message = null;
		try {
			if (livroDAO.save(livro) != null) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Livro cadastrado com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();

		return "livro";
	}

	public String alterar() {
		return "livro";
	}

	private Integer livroId;

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void excluir() {

		FacesMessage message = null;
		try {
			if (livroDAO.delete(livroId)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Autor excluída com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public String novo() {
		limparCarregar();

		return "livro";
	}

	public void onRowEdit(RowEditEvent event) {
		Livro livroEdit = (Livro) event.getObject();

		FacesMessage message = null;

		try {
			if (livroDAO.update(livroEdit)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Livro alterada com sucesso");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public void onRowCancel(RowEditEvent event) {
		Autor autorEdit = (Autor) event.getObject();

		FacesMessage msg = new FacesMessage("Edição cancelada", String.valueOf(autorEdit.getAutorId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	private void limparCarregar() {
		livro = new Livro();

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
