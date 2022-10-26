package it.magazzino.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "listino")
public class Listino {

	private Long idListino;
	private Prodotto prodotto;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataFineValid;
	private BigDecimal prezzo;

	public Listino() {
		super();
	}

	public Listino(Long idListino, Prodotto prodotto, Date dataFineValid, BigDecimal prezzo) {
		super();
		this.idListino = idListino;
		this.prodotto = prodotto;
		this.dataFineValid = dataFineValid;
		this.prezzo = prezzo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idListino")
	public Long getIdListino() {
		return idListino;
	}

	public void setIdListino(Long idListino) {
		this.idListino = idListino;
	}

	@ManyToOne
	@JoinColumn(name = "idProdotto")
	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	@Column(name = "dataFineValid")
	public Date getDataFineValid() {
		return dataFineValid;
	}

	public void setDataFineValid(Date dataFineValid) {
		this.dataFineValid = dataFineValid;
	}

	@Column(name = "prezzo")
	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

}
