package br.com.fiap.nac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.nac.factory.ConnectionFactory;
import br.com.fiap.nac.to.Autor;


public class AutorDAO implements GenericDAO<Autor> {

	public List<Autor> findAutores(int first, int pageSize) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Autor> listAutores = new ArrayList<Autor>();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM AUTOR LIMIT ?, ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, first);
			preparedStatement.setInt(2, pageSize);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Autor autor = new Autor();
				autor.setAutorId(rs.getInt("ID_AUTOR"));
				autor.setNome(rs.getString("NOME"));
				autor.setSobreNome(rs.getString("SOBRENOME"));
				autor.setTelefone(rs.getString("TELEFONE"));
				autor.setCpf(rs.getString("CPF"));
				autor.setSexo(rs.getString("SEXO"));
				autor.setEmail(rs.getString("EMAIL"));
				autor.setIdade(rs.getInt("IDADE"));
				autor.setNacionalidade(rs.getString("Nacionalidade"));
				autor.setDescricao(rs.getString("DESCRICAO"));

				listAutores.add(autor);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return listAutores;
	}

	@Override
	public List<Autor> getAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		List<Autor> autores = new ArrayList<Autor>();
		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM AUTOR";

		try {

			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {

				Autor autor = new Autor();

				autor.setAutorId(rs.getInt("ID_AUTOR"));
				autor.setNome(rs.getString("NOME"));
				autor.setSobreNome(rs.getString("SOBRENOME"));
				autor.setTelefone(rs.getString("TELEFONE"));
				autor.setCpf(rs.getString("CPF"));
				autor.setSexo(rs.getString("SEXO"));
				autor.setEmail(rs.getString("EMAIL"));
				autor.setIdade(rs.getInt("IDADE"));
				autor.setNacionalidade(rs.getString("Nacionalidade"));
				autor.setDescricao(rs.getString("DESCRICAO"));

				autores.add(autor);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return autores;
	}

	@Override
	public Autor save(Autor object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO AUTOR (NOME, SOBRENOME, TELEFONE, CPF, SEXO, EMAIL, IDADE,"
								+ "NACIONALIDADE, DESCRICAO)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, object.getNome());
			preparedStatement.setString(2, object.getSobreNome());
			preparedStatement.setString(3, object.getTelefone());
			preparedStatement.setString(4, object.getCpf());
			preparedStatement.setString(5, object.getSexo());
			preparedStatement.setString(6, object.getEmail());
			preparedStatement.setInt(7, object.getIdade());
			preparedStatement.setString(8, object.getNacionalidade());
			preparedStatement.setString(9, object.getDescricao());
			

			if (preparedStatement.executeUpdate() == 1) {
				// execute insert SQL stetement
				resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next())
                {
                	object.setAutorId(resultSet.getInt(1));
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
	public Autor get(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Autor autor = new Autor();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM AUTOR WHERE ID_AUTOR = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, id);		

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				autor.setAutorId(rs.getInt("ID_AUTOR"));
				autor.setNome(rs.getString("NOME"));
				autor.setSobreNome(rs.getString("SOBRENOME"));
				autor.setTelefone(rs.getString("TELEFONE"));
				autor.setCpf(rs.getString("CPF"));
				autor.setSexo(rs.getString("SEXO"));
				autor.setEmail(rs.getString("EMAIL"));
				autor.setIdade(rs.getInt("IDADE"));
				autor.setNacionalidade(rs.getString("Nacionalidade"));
				autor.setDescricao(rs.getString("DESCRICAO"));
				
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return autor;
		
	}

	@Override
	public boolean update(Autor object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE mydb.autor SET NOME = ?,SOBRENOME= ?,TELEFONE = ?,CPF = ?,SEXO = ?,EMAIL = ?,IDADE = ?,NACIONALIDADE = ?,"
								+ "DESCRICAO = ? WHERE ID_AUTOR = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, object.getNome());
			preparedStatement.setString(2, object.getSobreNome());
			preparedStatement.setString(3, object.getTelefone());
			preparedStatement.setString(4, object.getCpf());
			preparedStatement.setString(5, object.getSexo());
			preparedStatement.setString(6, object.getEmail());
			preparedStatement.setInt(7, object.getIdade());
			preparedStatement.setString(8, object.getNacionalidade());
			preparedStatement.setString(9, object.getDescricao());
			preparedStatement.setInt(10, object.getAutorId());
			
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

	
	public boolean delete(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM AUTOR WHERE ID_AUTOR = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);

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

	@Override
	public boolean deleteAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		Statement statement = null;

		String deleteSQL = "DELETE FROM AUTOR;";

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
	public boolean delete(Autor object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
