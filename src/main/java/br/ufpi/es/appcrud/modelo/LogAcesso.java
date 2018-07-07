package br.ufpi.es.appcrud.modelo;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LogAcesso {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String email;

	@DateTimeFormat
	private Date data;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString(){
		return ("LogAcesso: "+ "id: " + this.id + " descrição: " + this.descricao + " e-mail: " + this.email + " data: " + this.data);
	}
}