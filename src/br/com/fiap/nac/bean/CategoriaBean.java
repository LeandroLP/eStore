package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.CategoriaDAO;
import br.com.fiap.nac.to.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaBean {

	private Categoria categoria;
	private CategoriaDAO categoriaDAO;
	// private LazyDataModel<Categoria> listCategoria;
	private List<Categoria> listCategoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CategoriaDAO getCategoriaDAO() {
		return categoriaDAO;
	}

	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	@PostConstruct
	public void init() {
		categoriaDAO = new CategoriaDAO();

		limparCarregar();
	}

	public String salvar() {
		FacesMessage message = null;
		try {
			if (categoria.getId() != null) {
				if (categoriaDAO.update(categoria)) {
					message = new FacesMessage("Categoria Alterado com sucesso!");
				}

			} else {

				if (categoriaDAO.save(categoria) != null) {
					message = new FacesMessage("Categoria cadastrada com sucesso!");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		limparCarregar();

		return "categoria";
	}

	public String alterar(Categoria categoria) {

		this.categoria = categoria;

		return "categoria";
	}

	public void excluir(Categoria categoria) {
		FacesMessage message = null;
		try {
			if (categoriaDAO.delete(categoria)) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Categoria excluída com sucesso!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		limparCarregar();
	}

	public String novo() {
		limparCarregar();

		return "categoria";
	}

	private void limparCarregar() {
		categoria = new Categoria();

		try {
			listCategoria = categoriaDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
