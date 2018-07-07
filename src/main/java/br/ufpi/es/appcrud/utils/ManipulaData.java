package br.ufpi.es.appcrud.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManipulaData {
	
	private DateFormat date;
	private String padraoData = "dd-MM-yyy HH:mm:ss";
	
	public ManipulaData(){
		this.date = new SimpleDateFormat(this.padraoData);
	}
	
	public ManipulaData(String padrao){
		this.date = new SimpleDateFormat(padrao);
	}
	
	public Date getData(){
		return (new Date());
	}

	public String getDataFormatada() {
		return (date.format(new Date()));
	}
	
}