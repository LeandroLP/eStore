package br.com.fiap.nac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.nac.factory.ConnectionFactory;
import br.com.fiap.nac.to.Editora;

public class EditoraDAO implements GenericDAO<Editora> {

	@Override
	public List<Editora> getAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		List<Editora> editoras = new ArrayList<Editora>();
		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM EDITORA";

		try {

			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				Editora editora = new Editora();
				editora.setEditoraId(rs.getInt("ID_EDITORA"));
				editora.setNome(rs.getString("NOME"));
				editora.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				editora.setEmail(rs.getString("EMAIL"));
				editora.setTelefone(rs.getString("TELEFONE"));
				editora.setCnpj(rs.getString("CNPJ"));
				editora.setIe(rs.getString("IE"));
				editora.setSite(rs.getString("SITE"));
				editora.setCep(rs.getString("CEP"));
				editora.setEndereco(rs.getString("ENDERECO"));
				editora.setNumero(rs.getString("NUMERO"));
				editora.setComplemento(rs.getString("COMPLEMENTO"));
				editora.setCidade(rs.getString("CIDADE"));
				editora.setBairro(rs.getString("BAIRRO"));
				editora.setEstado(rs.getString("ESTADO"));

				editoras.add(editora);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return editoras;
	}

	@Override
	public Editora save(Editora object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO EDITORA (NOME, RAZAO_SOCIAL, EMAIL, TELEFONE, CNPJ, IE, SITE, CEP,"
				+ "ENDERECO, NUMERO, COMPLEMENTO, CIDADE, BAIRRO, ESTADO)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, object.getNome());
			preparedStatement.setString(2, object.getRazaoSocial());
			preparedStatement.setString(3, object.getEmail());
			preparedStatement.setString(4, object.getTelefone());
			preparedStatement.setString(5, object.getCnpj());
			preparedStatement.setString(6, object.getIe());
			preparedStatement.setString(7, object.getSite());
			preparedStatement.setString(8, object.getCep());
			preparedStatement.setString(9, object.getEndereco());
			preparedStatement.setString(10, object.getNumero());
			preparedStatement.setString(11, object.getComplemento());
			preparedStatement.setString(12, object.getCidade());
			preparedStatement.setString(13, object.getBairro());
			preparedStatement.setString(14, object.getEstado());

			if (preparedStatement.executeUpdate() == 1) {
				// execute insert SQL stetement
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					object.setEditoraId(resultSet.getInt(1));
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
	public Editora get(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Editora editora = new Editora();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM EDITORA WHERE ID_EDITORA = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				editora.setEditoraId(rs.getInt("ID_EDITORA"));
				editora.setNome(rs.getString("NOME"));
				editora.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				editora.setEmail(rs.getString("EMAIL"));
				editora.setTelefone(rs.getString("TELEFONE"));
				editora.setCnpj(rs.getString("CNPJ"));
				editora.setIe(rs.getString("IE"));
				editora.setSite(rs.getString("SITE"));
				editora.setCep(rs.getString("CEP"));
				editora.setEndereco(rs.getString("ENDERECO"));
				editora.setNumero(rs.getString("NUMERO"));
				editora.setComplemento(rs.getString("COMPLEMENTO"));
				editora.setCidade(rs.getString("CIDADE"));
				editora.setBairro(rs.getString("BAIRRO"));
				editora.setEstado(rs.getString("ESTADO"));

			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return editora;

	}

	@Override
	public boolean update(Editora object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE EDITORA SET NOME = ?,RAZAO_SOCIAL= ?,EMAIL = ?, TELEFONE = ?,CNPJ = ?,IE = ?,SITE = ?,CEP = ?,ENDERECO = ?,"
				+ "NUMERO = ?, COMPLEMENTO = ?, CIDADE= ? ,BAIRRO = ? , ESTADO = ? WHERE ID_EDITORA = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, object.getNome());
			preparedStatement.setString(2, object.getRazaoSocial());
			preparedStatement.setString(3, object.getEmail());
			preparedStatement.setString(4, object.getTelefone());
			preparedStatement.setString(5, object.getCnpj());
			preparedStatement.setString(6, object.getIe());
			preparedStatement.setString(7, object.getSite());
			preparedStatement.setString(8, object.getCep());
			preparedStatement.setString(9, object.getEndereco());
			preparedStatement.setString(10, object.getNumero());
			preparedStatement.setString(11, object.getComplemento());
			preparedStatement.setString(12, object.getCidade());
			preparedStatement.setString(13, object.getBairro());
			preparedStatement.setString(14, object.getEstado());
			preparedStatement.setInt(15, object.getEditoraId());

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

		String deleteSQL = "DELETE FROM EDITORA;";

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
	public boolean delete(Editora object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM EDITORA WHERE ID_EDITORA = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, object.getEditoraId());

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
