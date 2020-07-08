package it.polito.tdp.crimes.model;

public class InfoArco implements Comparable<InfoArco>{
	
	private Distretto distretto;
	private Double peso;
	public InfoArco(Distretto distretto, Double peso) {
		super();
		this.distretto = distretto;
		this.peso = peso;
	}
	public Distretto getDistretto() {
		return distretto;
	}
	public void setDistretto(Distretto distretto) {
		this.distretto = distretto;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(InfoArco o) {
		// TODO Auto-generated method stub
		return Double.compare(this.peso, o.peso);
	}
	
	

}
