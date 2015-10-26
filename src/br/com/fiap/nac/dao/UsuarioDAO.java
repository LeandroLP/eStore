package br.com.fiap.nac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.nac.factory.ConnectionFactory;
import br.com.fiap.nac.to.TipoAcesso;
import br.com.fiap.nac.to.Usuario;

public class UsuarioDAO implements GenericDAO<Usuario> {

	public List<Usuario> findUsuarioes(int first, int pageSize) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Usuario> listUsuarioes = new ArrayList<Usuario>();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM USUARIO LIMIT ?, ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, first);
			preparedStatement.setInt(2, pageSize);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setUsuarioId(rs.getInt("ID_USUARIO"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setCpf(rs.getString("CPF_CNPJ"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSexo(rs.getString("SEXO"));
				usuario.setTelefone(rs.getString("TELEFONE"));
				usuario.setCep(rs.getString("CEP"));
				usuario.setLogradouro(rs.getString("LOGRADOURO"));
				usuario.setBairro(rs.getString("BAIRRO"));
				usuario.setNumero(rs.getString("NUMERO"));
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setCidade(rs.getString("CIDADE"));
				usuario.setEstado(rs.getString("ESTADO"));
				usuario.setTipoAcessoId(rs.getInt("ID_TIPO_ACESSO"));

				listUsuarioes.add(usuario);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return listUsuarioes;
	}

	@Override
	public List<Usuario> getAll() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		List<Usuario> usuarioes = new ArrayList<Usuario>();
		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM USUARIO";

		try {

			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {

				Usuario usuario = new Usuario();

				usuario.setUsuarioId(rs.getInt("ID_USUARIO"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setCpf(rs.getString("CPF_CNPJ"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSexo(rs.getString("SEXO"));
				usuario.setTelefone(rs.getString("TELEFONE"));
				usuario.setCep(rs.getString("CEP"));
				usuario.setLogradouro(rs.getString("LOGRADOURO"));
				usuario.setBairro(rs.getString("BAIRRO"));
				usuario.setNumero(rs.getString("NUMERO"));
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setCidade(rs.getString("CIDADE"));
				usuario.setEstado(rs.getString("ESTADO"));
				usuario.setTipoAcessoId(rs.getInt("ID_TIPO_ACESSO"));

				usuarioes.add(usuario);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return usuarioes;
	}
	
	public boolean logar(Usuario object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT COUNT(*) FROM usuario WHERE login = ? AND senha = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setString(1, object.getLogin());
			preparedStatement.setString(2, object.getSenha());

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int numberOfRows = rs.getInt(1);
				if(numberOfRows > 0){
					return true;
				}
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
	public Usuario save(Usuario object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String insertTableSQL = "INSERT INTO `mydb`.`usuario` (`ID_USUARIO`,`NOME`,`CPF_CNPJ`,`EMAIL`,`SEXO`,`TELEFONE`,"
				+ "`CEP`,`LOGRADOURO`,`BAIRRO`,`NUMERO`,`LOGIN`,`SENHA`,`CIDADE`,`ESTADO`,`ID_TIPO_ACESSO`)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, object.getNome());
			preparedStatement.setString(2, object.getCpf());
			preparedStatement.setString(3, object.getEmail());
			preparedStatement.setString(4, object.getSexo());
			preparedStatement.setString(5, object.getTelefone());
			preparedStatement.setString(6, object.getCep());
			preparedStatement.setString(7, object.getLogradouro());
			preparedStatement.setString(8, object.getBairro());
			preparedStatement.setString(9, object.getNumero());
			preparedStatement.setString(10, object.getLogin());
			preparedStatement.setString(11, object.getSenha());
			preparedStatement.setString(12, object.getCidade());
			preparedStatement.setString(13, object.getEstado());
			preparedStatement.setInt(14, object.getTipoAcessoId());

			if (preparedStatement.executeUpdate() == 1) {
				// execute insert SQL stetement
				resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					object.setUsuarioId(resultSet.getInt(1));
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
	public Usuario get(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);

			preparedStatement.setInt(1, id);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				usuario.setUsuarioId(rs.getInt("ID_USUARIO"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setCpf(rs.getString("CPF_CNPJ"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSexo(rs.getString("SEXO"));
				usuario.setTelefone(rs.getString("TELEFONE"));
				usuario.setCep(rs.getString("CEP"));
				usuario.setLogradouro(rs.getString("LOGRADOURO"));
				usuario.setBairro(rs.getString("BAIRRO"));
				usuario.setNumero(rs.getString("NUMERO"));
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setCidade(rs.getString("CIDADE"));
				usuario.setEstado(rs.getString("ESTADO"));
				usuario.setTipoAcessoId(rs.getInt("ID_TIPO_ACESSO"));

			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return usuario;

	}

	@Override
	public boolean update(Usuario object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE `mydb`.`usuario` SET `ID_USUARIO` =?,`NOME` = ?,`CPF_CNPJ` = ?,`EMAIL` = ?,`SEXO` = ?,"
				+ "`TELEFONE` = ?,`CEP` = ?,`LOGRADOURO` = ?,`BAIRRO` = ?,`NUMERO` = ?,`LOGIN` = ?,`SENHA` = ?,"
				+ "`CIDADE` = ?,`ESTADO` = ?,`ID_TIPO_ACESSO` = ? WHERE `ID_USUARIO` = ?;";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);

			preparedStatement.setString(1, object.getNome());
			preparedStatement.setString(2, object.getCpf());
			preparedStatement.setString(3, object.getEmail());
			preparedStatement.setString(4, object.getSexo());
			preparedStatement.setString(5, object.getTelefone());
			preparedStatement.setString(6, object.getCep());
			preparedStatement.setString(7, object.getLogradouro());
			preparedStatement.setString(8, object.getBairro());
			preparedStatement.setString(9, object.getNumero());
			preparedStatement.setString(10, object.getLogin());
			preparedStatement.setString(11, object.getSenha());
			preparedStatement.setString(12, object.getCidade());
			preparedStatement.setString(13, object.getEstado());
			preparedStatement.setInt(14, object.getTipoAcessoId());

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

		String deleteSQL = "DELETE FROM USUARIO WHERE ID_USUARIO = ?;";

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

		String deleteSQL = "DELETE FROM USUARIO;";

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

	public Usuario efetuarLogin(String login, String senha) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Usuario usuario = new Usuario();
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "SELECT * FROM usuario WHERE LOGIN = ? AND SENHA = ?";

		try {
			dbConnection = ConnectionFactory.getConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, senha);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {

				usuario.setUsuarioId(rs.getInt("ID_USUARIO"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setCpf(rs.getString("CPF_CNPJ"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSexo(rs.getString("SEXO"));
				usuario.setTelefone(rs.getString("TELEFONE"));
				usuario.setCep(rs.getString("CEP"));
				usuario.setLogradouro(rs.getString("LOGRADOURO"));
				usuario.setBairro(rs.getString("BAIRRO"));
				usuario.setNumero(rs.getString("NUMERO"));
				usuario.setLogin(rs.getString("LOGIN"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setCidade(rs.getString("CIDADE"));
				usuario.setEstado(rs.getString("ESTADO"));
				usuario.setTipoAcessoId(rs.getInt("ID_TIPO_ACESSO"));

			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return usuario;
	}

	public List<TipoAcesso> listarTipoAceso() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		List<TipoAcesso> listaTipoAcesso = new ArrayList<TipoAcesso>();
		Connection dbConnection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM USUARIO";

		try {

			dbConnection = ConnectionFactory.getConnection();
			statement = dbConnection.createStatement();

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {

				TipoAcesso tipoAcesso = new TipoAcesso();

				tipoAcesso.setIdTipoAcesso(rs.getInt(""));
				tipoAcesso.setDescricao(rs.getString(""));

				listaTipoAcesso.add(tipoAcesso);
			}
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}

		return listaTipoAcesso;
	}

	@Override
	public boolean delete(Usuario object) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
