package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PacienteDAO {
	public void create(PacienteDTO pacienteDTO) throws Exception {
		// Conex‹o BD
		Connection con = Conexao.getClonexao();
		PreparedStatement psmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO PACIENTES(COD, NOME, END, CONTATO) VALUES(?,?,?,?)");
			psmt = con.prepareStatement(sql.toString());

			psmt.setInt(1, pacienteDTO.getCod());
			psmt.setString(2, pacienteDTO.getNome());
			psmt.setString(3, pacienteDTO.getEnd());
			psmt.setString(4, pacienteDTO.getContato());
			psmt.execute();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			psmt.close();
			con.close();
		}
	}

	public List list()throws Exception {
		Connection con = Conexao.getClonexao();
		ResultSet rs = null;
		PreparedStatement psmt = null;

		String sql = "SELECT * FROM PACIENTES";
		ArrayList pacientes = new ArrayList();

		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			PacienteDTO pacienteDTO = null;

			while(rs.next()) {
				pacienteDTO = new PacienteDTO();

				pacienteDTO.setCod(rs.getInt("COD"));
				pacienteDTO.setNome(rs.getString("NOME"));
				pacienteDTO.setEnd(rs.getString("END"));
				pacienteDTO.setContato(rs.getString("CONTATO"));

				pacientes.add(pacienteDTO);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			psmt.close();
			rs.close();
			con.close();
		}
		return pacientes;
	}

	public void delete(PacienteDTO pacienteDTO) throws Exception {
		Connection con = Conexao.getClonexao();
		PreparedStatement psmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM PACIENTES WHERE COD = ?");
			psmt = con.prepareStatement(sql.toString());

			psmt.setInt(1, pacienteDTO.getCod());
			psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			psmt.close();
			con.close();
		}
	}

	public void update(PacienteDTO pacienteDTO) throws Exception {
		// Conex‹o BD
		Connection con = Conexao.getClonexao();
		PreparedStatement psmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE PACIENTES SET NOME = ?, END = ?, CONTATO = ? WHERE COD = ?");
			psmt = con.prepareStatement(sql.toString());

			psmt.setString(1, pacienteDTO.getNome());
			psmt.setString(2, pacienteDTO.getEnd());
			psmt.setString(4, pacienteDTO.getContato());
			psmt.setInt(5, pacienteDTO.getCod());
			psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			psmt.close();
			con.close();
		}
	}

	public boolean unique(PacienteDTO pacienteDTO) throws Exception {
		// Conex‹o BD
		Connection con = Conexao.getClonexao();
		ResultSet rs = null;
		PreparedStatement psmt = null;

		try {
			String sql = "SELECT NOME FROM PACIENTES WHERE COD = ?";

			psmt.setInt(1, pacienteDTO.getCod());
			psmt = con.prepareStatement(sql.toString());
			rs = psmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			psmt.close();
			con.close();
		}

		while(rs.next()) {
			if (rs.getInt("COD") == pacienteDTO.getCod()) {
				return true;
			}
		}
		return false;

	}
}
