package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.EditoraDAO;
import br.com.fiap.nac.to.Editora;

@ManagedBean
@SessionScoped
public class EditoraBean {

	private Editora editora;
	// private LazyDataModel<Editora> listEditora;
	private EditoraDAO editoraDAO;
	private List<Editora> listEditora;

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public EditoraDAO getEditoraDAO() {
		return this.editoraDAO;
	}

	public void setEditoraDAO(EditoraDAO editoraDAO) {
		this.editoraDAO = editoraDAO;
	}

	public List<Editora> getListEditora() {
		return listEditora;
	}

	public void setListEditora(List<Editora> listEditora) {
		this.listEditora = listEditora;
	}

	@PostConstruct
	public void init() {
		editoraDAO = new EditoraDAO();

		limparCarregar();
	}

	public String salvar() {

		FacesMessage message = null;
		try {
			if (editoraDAO.save(editora) != null) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Editora cadastrado com sucesso!");
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

		return "editora";
	}

	public String alterar() {
		return "editora";
	}

	private Integer editoraId;

	public Integer getEditoraId() {
		return editoraId;
	}

	public void setEditoraId(Integer editoraId) {
		this.editoraId = editoraId;
	}

	public void excluir() {

		FacesMessage message = null;
		try {
			if (editoraDAO.delete(editoraId)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Editora excluída com sucesso!");
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

		return "editora";
	}

	private void limparCarregar() {
		editora = new Editora();

		try {
			listEditora = editoraDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
