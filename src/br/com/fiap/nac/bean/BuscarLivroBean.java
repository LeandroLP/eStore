package br.com.fiap.nac.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.LivroDAO;
import br.com.fiap.nac.to.BuscarLivro;
import br.com.fiap.nac.to.Livro;

@ManagedBean
@SessionScoped
public class BuscarLivroBean {

	private BuscarLivro buscarLivro;
	private LivroDAO livroDAO;
	private List<Livro> listLivro;

	public BuscarLivro getBuscarLivro() {
		return buscarLivro;
	}

	public void setBuscarLivro(BuscarLivro buscarLivro) {
		this.buscarLivro = buscarLivro;
	}

	public List<Livro> getListLivro() {
		return listLivro;
	}

	public void setListLivro(List<Livro> listLivro) {
		this.listLivro = listLivro;
	}

	@PostConstruct
	public void init() {
		buscarLivro = new BuscarLivro();
		livroDAO = new LivroDAO();

		limparCarregar();
	}

	private void limparCarregar() {
		listLivro = new ArrayList<>();
	}
	
	public void buscar(){
		FacesMessage message = null;
		try {			
			listLivro = livroDAO.bus
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
	}

}
