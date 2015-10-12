package br.com.fiap.nac.to;

import java.util.List;

import br.com.fiap.nac.bean.GeneroBean;

public class Livro {

	private Integer livroId;
	private String titulo;
	private String descricao;
	private Double valor;
	private Integer isbn;
	private Integer numeroPaginas;
	private Integer curtidas;
	private Integer ano;
	private String idioma;
	private byte[] imagem;
	private Integer autorId;
	private Integer editoraId;
	private Integer categoriaId;
	private Integer generoId;
	private Autor autor;
	private List<Autor> listAutor;

	private Categoria categoria;
	private List<Categoria> listCategoria;

	private Editora editora;
	private List<Editora> listEditora;

	private GeneroBean genero;
	private List<Genero> listGenero;

	public List<Autor> getListAutor() {
		return listAutor;
	}

	public void setListAutor(List<Autor> listAutor) {
		this.listAutor = listAutor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Editora> getListEditora() {
		return listEditora;
	}

	public void setListEditora(List<Editora> listEditora) {
		this.listEditora = listEditora;
	}

	public GeneroBean getGenero() {
		return genero;
	}

	public void setGenero(GeneroBean genero) {
		this.genero = genero;
	}

	public List<Genero> getListGenero() {
		return listGenero;
	}

	public void setListGenero(List<Genero> listGenero) {
		this.listGenero = listGenero;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Integer getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(Integer curtidas) {
		this.curtidas = curtidas;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getEditoraId() {
		return editoraId;
	}

	public void setEditoraId(Integer editoraId) {
		this.editoraId = editoraId;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getGeneroId() {
		return generoId;
	}

	public void setGeneroId(Integer generoId) {
		this.generoId = generoId;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
