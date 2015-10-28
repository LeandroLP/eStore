package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.LivroDAO;
import br.com.fiap.nac.to.Livro;

@ManagedBean
@SessionScoped
public class BuscarLivroBean {

	private String busca;
	private LivroDAO livroDAO;
	private List<Livro> listLivro;

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<Livro> getListLivro() {
		return listLivro;
	}

	public void setListLivro(List<Livro> listLivro) {
		this.listLivro = listLivro;
	}

	@PostConstruct
	public void init() {
		livroDAO = new LivroDAO();

		limparCarregar();
	}

	private void limparCarregar() {
		busca = null;
		listLivro = new ArrayList<>();
	}

	public String buscar(){
		FacesMessage message = null;
		try {			
			listLivro = livroDAO.buscarLivroAutorEditora(busca);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = new FacesMessage(e.getMessage());
		}

		if(message != null){
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return null;
	}
	
	public String novo(){
		limparCarregar();
		
		return null;
	}

}
