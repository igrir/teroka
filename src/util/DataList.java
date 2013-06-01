package util;

import java.io.Serializable;

public class DataList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5021083514275598722L;
	public String nama;
	public String status;
	public String harga;
	public String syarat_step;
	
	public DataList(String nama, String status, String harga, String syarat){
		this.nama = nama;
		this.status = status;
		this.harga = harga;
		this.syarat_step = syarat;
	}
	
	public DataList(){
		
	}
	
	public String getNama(){
		return nama;
	}
	
	public void setNama(String nama){
		this.nama = nama;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getHarga(){
		return harga;
	}
	
	public void setHarga(String harga){
		this.harga = harga;
	}
	
	public String getSaratStep(){
		return syarat_step;
	}
	
	public void setSaratStep(String sarat_step){
		this.syarat_step= sarat_step;
	}
	


}

