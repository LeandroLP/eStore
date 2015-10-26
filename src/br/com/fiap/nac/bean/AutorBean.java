package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.AutorDAO;
import br.com.fiap.nac.to.Autor;

@ManagedBean
@SessionScoped
public class AutorBean {

	private Autor autor;
	private AutorDAO autorDAO;
	private List<Autor> listAutor;

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public AutorDAO getAutorDAO() {
		return this.autorDAO;
	}

	public void setAutorDAO(AutorDAO autorDAO) {
		this.autorDAO = autorDAO;
	}

	public List<Autor> getListAutor() {
		return listAutor;
	}

	public void setListAutor(List<Autor> listAutor) {
		this.listAutor = listAutor;
	}

	@PostConstruct
	public void init() {
		autorDAO = new AutorDAO();
		autor = new Autor();

		setListAutor(new ArrayList<Autor>());
		try {
			listAutor = autorDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String salvar() {

		FacesMessage message = null;
		try {
			if (autor.getAutorId() != null) {

				if (autorDAO.update(autor)) {
					message = new FacesMessage("Autor Alterado com sucesso!");
				}

			} else {

				if (autorDAO.save(autor) != null) {
					message = new FacesMessage("Autor cadastrado com sucesso!");
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

		return "autor";
	}

	public String alterar(Autor autor) {
		this.autor = autor;
		return "autor";
	}

	public String excluir(Autor autor) {

		FacesMessage message = null;
		try {
			if (autorDAO.delete(autor)) {
				message = new FacesMessage("Autor excluído com sucesso!");
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
		return "autor";
	}

	public String novo() {
		limparCarregar();

		return "autor";
	}

	private void limparCarregar() {
		autor = new Autor();

		try {
			listAutor = autorDAO.getAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
