package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.GeneroDAO;
import br.com.fiap.nac.to.Genero;

@ManagedBean
@SessionScoped
public class GeneroBean {

	private Genero genero;
	private GeneroDAO generoDAO;
	// private LazyDataModel<Genero> listGenero;
	private List<Genero> listGenero;

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public GeneroDAO getGeneroDAO() {
		return generoDAO;
	}

	public void setGeneroDAO(GeneroDAO generoDAO) {
		this.generoDAO = generoDAO;
	}

	public List<Genero> getListGenero() {
		return listGenero;
	}

	public void setListGenero(List<Genero> listGenero) {
		this.listGenero = listGenero;
	}

	@PostConstruct
	public void init() {
		generoDAO = new GeneroDAO();

		limparCarregar();
	}

	public String salvar() {
		FacesMessage message = null;
		try {
			if (genero.getId() != null) {
				if (generoDAO.update(genero)) {
					message = new FacesMessage("Genero Alterado com sucesso!");
				}

			} else {
				if (generoDAO.save(genero) != null) {
					message = new FacesMessage("Genero cadastrado com sucesso!");
				}
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

		return "genero";
	}

	public String alterar(Genero genero) {

		this.genero = genero;
		return "genero";
	}


	public void excluir(Genero genero) {
		FacesMessage message = null;
		try {
			if (generoDAO.delete(genero)) {
				message = new FacesMessage("Genero excluída com sucesso!");
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
		// return "genero";
	}

	public String novo() {
		limparCarregar();

		return "genero";
	}

	private void limparCarregar() {
		genero = new Genero();

		try {
			listGenero = generoDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
