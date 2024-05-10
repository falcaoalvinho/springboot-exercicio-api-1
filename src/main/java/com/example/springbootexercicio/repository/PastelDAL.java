package com.example.springbootexercicio.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springbootexercicio.model.Pastel;

@Repository
public class PastelDAL{

	String SELECT_ALL = "SELECT id, nome, descricao FROM pasteis";
	
	String INSERT_UNIT = "INSERT INTO pasteis(nome, descricao) VALUES (?, ?)";
	
	String DELETE_UNIT = "DELETE FROM pasteis WHERE id = ?";
	
	String UPDATE_UNIT = "UPDATE pasteis SET nome = ?, descricao = ? WHERE id = ?";
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Pastel> Read() {
		return jdbc.query(SELECT_ALL, (rs, rowNum) -> {
			return new Pastel(rs.getLong("id"),rs.getString("nome"),rs.getString("descricao"));
			});
		}
	
	public int Create(Pastel pastel) {
		Object[] parameters = {pastel.getNome(), pastel.getDescricao()};
		return jdbc.update(INSERT_UNIT, parameters);
	}
	
	public int Delete(Pastel pastel) {
		Object[] parameters = {pastel.getId()};
		return jdbc.update(DELETE_UNIT, parameters);
	}
	
	public int Update(Pastel pastel, Long id) {
		Object[] parameters = {pastel.getNome(), pastel.getDescricao(), pastel.getId()};
		
		return jdbc.update(UPDATE_UNIT, parameters);
	}
	
	
}
