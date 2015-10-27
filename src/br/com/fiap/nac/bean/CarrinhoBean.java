package br.com.fiap.nac.bean;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.nac.dao.CarrinhoDAO;
import br.com.fiap.nac.to.Carrinho;
import br.com.fiap.nac.to.Livro;

@ManagedBean
@SessionScoped
public class CarrinhoBean {

	private CarrinhoDAO carrinhoDAO;
	private Carrinho carrinho;
	private List<Carrinho> listCarrinho;
	private Double valorTotal;

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public List<Carrinho> getListCarrinho() {
		return listCarrinho;
	}

	public void setListCarrinho(List<Carrinho> listCarrinho) {
		this.listCarrinho = listCarrinho;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@PostConstruct
	public void init() {
		carrinhoDAO = new CarrinhoDAO();

		limparCarregar();
	}

	public String adicionar() {
		FacesMessage message = null;
		try {
			carrinho.setData(new Date());

			if (carrinhoDAO.save(carrinho) != null) {
				message = new FacesMessage("Item adicionado com sucesso!");
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

		return null;
	}

	public String adicionarListaDesejo() {
		FacesMessage message = null;
		try {
			carrinhoDAO.save(carrinho);
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

	private void limparCarregar() {
		carrinho = new Carrinho();

		FacesMessage message = null;
		try {
			listCarrinho = carrinhoDAO.getAll();
			
			calcularValorTotal();
		} catch (ClassNotFoundException e) {
			message = new FacesMessage(e.getMessage());
		} catch (SQLException e) {
			message = new FacesMessage(e.getMessage());
		}

		if (message != null) {
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String selecionarLivro(Livro livro) {
		carrinho.setLivro(livro);

		return "item";
	}
	
	public void calcularValorTotal(){
		Double valorTotal = 0.0;
		for(int i = 0; i < listCarrinho.size(); i++){
			valorTotal += listCarrinho.get(i).getTotal();
		}
		
		setValorTotal(valorTotal);
	}

}
