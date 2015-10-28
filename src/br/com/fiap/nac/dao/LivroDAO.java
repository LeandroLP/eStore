package br.com.fiap.nac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.fiap.nac.factory.ConnectionFactory;
import br.com.fiap.nac.to.BuscarLivro;
import br.com.fiap.nac.to.Categoria;
import br.com.fiap.nac.to.Livro;

public class LivroDAO implements GenericDAO<Livro> {

	@Override
	public List<Livro> getAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Livro> listLivro = new ArrayList<Livro>();
		Connection dbConnection = null;
		Statement statement = null;

		String selectTableSQL = "SELECT * FROM LIVRO";

		try {

			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {

				Livro livro = new Livro();

				livro.setLivroId(rs.getInt("ID_LIVRO"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setDescricao(rs.getString("DESCRICAO"));
				livro.setValor(rs.getDouble("VALOR"));
				livro.setIsbn(rs.getInt("ISBN"));
				livro.setNumeroPaginas(rs.getInt("NUMERO_PAGINA"));
				livro.setCurtidas(rs.getInt("CURTIDA"));
				livro.setAno(rs.getInt("ANO"));
				livro.setIdioma(rs.getString("IDIOMA"));
				livro.setImagem(rs.getBinaryStream("IMAGEM"));
				livro.setImagem2(Base64.encodeBase64String(rs.getBytes("IMAGEM")));
				livro.setAutorId(rs.getInt("ID_AUTOR"));
				livro.setCategoriaId(rs.getInt("ID_CATEGORIA"));
				livro.setEditoraId(rs.getInt("ID_EDITORA"));
				livro.setGeneroId(rs.getInt("ID_GENERO"));

				listLivro.add(livro);
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return listLivro;
	}
	
	public List<Livro> getByCategoria(Categoria object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Livro> listLivro = new ArrayList<Livro>();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM LIVRO WHERE ID_CATEGORIA = ?";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, object.getId());

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Livro livro = new Livro();

				livro.setLivroId(rs.getInt("ID_LIVRO"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setDescricao(rs.getString("DESCRICAO"));
				livro.setValor(rs.getDouble("VALOR"));
				livro.setIsbn(rs.getInt("ISBN"));
				livro.setNumeroPaginas(rs.getInt("NUMERO_PAGINA"));
				livro.setCurtidas(rs.getInt("CURTIDA"));
				livro.setAno(rs.getInt("ANO"));
				livro.setIdioma(rs.getString("IDIOMA"));
				livro.setImagem(rs.getBinaryStream("IMAGEM"));
				livro.setImagem2(Base64.encodeBase64String(rs.getBytes("IMAGEM")));
				livro.setAutorId(rs.getInt("ID_AUTOR"));
				livro.setCategoriaId(rs.getInt("ID_CATEGORIA"));
				livro.setEditoraId(rs.getInt("ID_EDITORA"));
				livro.setGeneroId(rs.getInt("ID_GENERO"));

				listLivro.add(livro);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return listLivro;
	}
	
	public List<Livro> getByCategoria(BuscarLivro buscarLivro) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Livro> listLivro = new ArrayList<Livro>();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM LIVRO LEFT JOIN WHERE NOME = ? OR ";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setString(1, buscarLivro.getNome());
			preparedStatement.setString(2, buscarLivro.getNomeAutor());
			preparedStatement.setString(3, buscarLivro.getNomeEditora());

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				Livro livro = new Livro();

				livro.setLivroId(rs.getInt("ID_LIVRO"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setDescricao(rs.getString("DESCRICAO"));
				livro.setValor(rs.getDouble("VALOR"));
				livro.setIsbn(rs.getInt("ISBN"));
				livro.setNumeroPaginas(rs.getInt("NUMERO_PAGINA"));
				livro.setCurtidas(rs.getInt("CURTIDA"));
				livro.setAno(rs.getInt("ANO"));
				livro.setIdioma(rs.getString("IDIOMA"));
				livro.setImagem(rs.getBinaryStream("IMAGEM"));
				livro.setImagem2(Base64.encodeBase64String(rs.getBytes("IMAGEM")));
				livro.setAutorId(rs.getInt("ID_AUTOR"));
				livro.setCategoriaId(rs.getInt("ID_CATEGORIA"));
				livro.setEditoraId(rs.getInt("ID_EDITORA"));
				livro.setGeneroId(rs.getInt("ID_GENERO"));

				listLivro.add(livro);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return listLivro;
	}

	@Override
	public Livro save(Livro object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO livro"
				+ "(TITULO,DESCRICAO,VALOR,ISBN,NUMERO_PAGINA,CURTIDA,ANO,IDIOMA,"
				+ "IMAGEM,ID_AUTOR,ID_CATEGORIA,ID_EDITORA,ID_GENERO)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, object.getTitulo());
			preparedStatement.setString(2, object.getDescricao());
			preparedStatement.setDouble(3, object.getValor());
			preparedStatement.setInt(4, object.getIsbn());
			preparedStatement.setInt(5, object.getNumeroPaginas());
			preparedStatement.setInt(6, object.getCurtidas());
			preparedStatement.setInt(7, object.getAno());
			preparedStatement.setString(8, object.getIdioma());
			preparedStatement.setBlob(9, object.getImagem());
			preparedStatement.setInt(10, object.getAutorId());
			preparedStatement.setInt(11, object.getCategoriaId());
			preparedStatement.setInt(12, object.getEditoraId());
			preparedStatement.setInt(13, object.getGeneroId());

			if (preparedStatement.executeUpdate() == 1) {
				// execute insert SQL stetement
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					object.setLivroId(resultSet.getInt(1));
				}
				return object;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;
	}

	@Override
	public Livro get(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM LIVRO WHERE ID_LIVRO = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setLivroId(rs.getInt("ID_LIVRO"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setDescricao(rs.getString("DESCRICAO"));
				livro.setValor(rs.getDouble("VALOR"));
				livro.setIsbn(rs.getInt("ISBN"));
				livro.setNumeroPaginas(rs.getInt("NUMERO_PAGINA"));
				livro.setCurtidas(rs.getInt("CURTIDA"));
				livro.setAno(rs.getInt("ANO"));
				livro.setIdioma(rs.getString("IDIOMA"));
				livro.setImagem(rs.getBinaryStream("IMAGEM"));
				livro.setImagem2(Base64.encodeBase64String(rs.getBytes("IMAGEM")));
				livro.setAutorId(rs.getInt("ID_AUTOR"));
				livro.setCategoriaId(rs.getInt("ID_CATEGORIA"));
				livro.setEditoraId(rs.getInt("ID_EDITORA"));
				livro.setGeneroId(rs.getInt("ID_GENERO"));
				return livro;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return null;

	}

	@Override
	public boolean update(Livro object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE `livro` SET"
				+ "`TITULO` = ?,`DESCRICAO` = ?,`VALOR` = ?,`ISBN` = ?,`NUMERO_PAGINA` = ?,`CURTIDA` = ?,"
				+ "`ANO` = ?,`IDIOMA` = ?,`IMAGEM` = ?,`ID_AUTOR` = ?,`ID_CATEGORIA` = ?,`ID_EDITORA` = ?,"
				+ "`ID_GENERO` = ? WHERE `ID_LIVRO` = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, object.getTitulo());
			preparedStatement.setString(2, object.getDescricao());
			preparedStatement.setDouble(3, object.getValor());
			preparedStatement.setInt(4, object.getIsbn());
			preparedStatement.setInt(5, object.getNumeroPaginas());
			preparedStatement.setInt(6, object.getCurtidas());
			preparedStatement.setInt(7, object.getAno());
			preparedStatement.setString(8, object.getIdioma());
			preparedStatement.setBlob(9, object.getImagem());
			preparedStatement.setInt(10, object.getAutorId());
			preparedStatement.setInt(11, object.getCategoriaId());
			preparedStatement.setInt(12, object.getEditoraId());
			preparedStatement.setInt(13, object.getGeneroId());
			preparedStatement.setInt(14, object.getLivroId());

			// execute update SQL stetement
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return false;
	}

	@Override
	public boolean deleteAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		Statement statement = null;

		String deleteSQL = "DELETE FROM livro;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute delete SQL stetement
			if (statement.executeUpdate(deleteSQL) == 1) {
				return true;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Livro object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM livro WHERE ID_LIVRO = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, object.getLivroId());

			// execute delete SQL stetement
			if (preparedStatement.executeUpdate() == 1) {
				return true;
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return false;
	}

}
