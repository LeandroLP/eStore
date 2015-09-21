package br.com.fiap.nac.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.fiap.nac.to.Genero;

@ManagedBean
@SessionScoped
public class LivroBean {

	public LivroBean() {
		AutorBean autorBean = new AutorBean();
		autorBean.setNome("ASD");
		this.listAutor = new ArrayList<AutorBean>();
		this.listAutor.add(autorBean);

		CategoriaBean categoriaBean = new CategoriaBean();
		categoriaBean.setDescricao("ASD");
		this.listCategoria = new ArrayList<CategoriaBean>();
		this.listCategoria.add(categoriaBean);

		EditoraBean editoraBean = new EditoraBean();
		editoraBean.setNome("ASD");
		this.listEditora = new ArrayList<EditoraBean>();
		this.listEditora.add(editoraBean);

		Genero generoBean = new Genero();
		generoBean.setDescricao("ASD");
		this.listGenero = new ArrayList<Genero>();
		this.listGenero.add(generoBean);
	}

	private Integer id;
	private String titulo;
	private String descricao;
	private Double valor;
	private Long isbn;
	private Integer numeroPaginas;
	private Integer curtidas;
	private Date ano;
	private String idioma;
	private byte[] imagem;

	private AutorBean autor;
	private List<AutorBean> listAutor;

	private CategoriaBean categoria;
	private List<CategoriaBean> listCategoria;

	private EditoraBean editora;
	private List<EditoraBean> listEditora;

	private GeneroBean genero;
	private List<Genero> listGenero;

	private String mensagem;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
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

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
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

	public AutorBean getAutor() {
		return autor;
	}

	public void setAutor(AutorBean autor) {
		this.autor = autor;
	}

	public List<AutorBean> getListAutor() {
		return listAutor;
	}

	public void setListAutor(List<AutorBean> listAutor) {
		this.listAutor = listAutor;
	}

	public CategoriaBean getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaBean categoria) {
		this.categoria = categoria;
	}

	public List<CategoriaBean> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<CategoriaBean> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public EditoraBean getEditora() {
		return editora;
	}

	public void setEditora(EditoraBean editora) {
		this.editora = editora;
	}

	public List<EditoraBean> getListEditora() {
		return listEditora;
	}

	public void setListEditora(List<EditoraBean> listEditora) {
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	// Persistencia
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful", "Your message: FileUploaded"));
		return "livro";
	}

	public void fileUploadListener(FileUploadEvent event) {
		this.file = event.getFile();

		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String alterar() {
		return "livro";
	}

	public String excluir() {
		return "livro";
	}

	public String novo() {
		return "livro";
	}
}
