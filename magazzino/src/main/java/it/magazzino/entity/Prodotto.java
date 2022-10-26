package it.magazzino.entity;

import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;


import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import it.magazzino.resources.MyDateDeserializer;

@Entity
@Table(name = "prodotto")
public class Prodotto {

	private Long id;
	private String nome;
	private Categoria categoria;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInizioProd;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataFineProd;
	
	ObjectMapper om=new ObjectMapper();

	public Prodotto(Long id, String nome, Categoria categoria, Date dataInizioProd, Date dataFineProd) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.dataInizioProd = dataInizioProd;
		this.dataFineProd = dataFineProd;
	}

	public Prodotto() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProdotto")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nomeProdotto")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(name = "idCategoria")
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "dataInizioProd")
	public Date getDataInizioProd() {
		return dataInizioProd;
	}

	public void setDataInizioProd(Date dataInizioProd) {
		this.dataInizioProd = dataInizioProd;
	}

	@Column(name = "dataFineProd")
	public Date getDataFineProd() {
		return dataFineProd;
	}

	public void setDataFineProd(Date dataFineProd) {
		this.dataFineProd = dataFineProd;
	}
	
	
}
