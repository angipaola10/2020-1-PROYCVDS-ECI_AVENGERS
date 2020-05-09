package edu.eci.cvds.entities;

import org.primefaces.model.SelectableDataModel;

public class PalabraClave implements SelectableDataModel{

	private int id;
	private String palabraClave;
	
	public PalabraClave() {}
	
	public PalabraClave(String palabraClave) {
		this.palabraClave= palabraClave;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}
	
	public String toString() {
		return  palabraClave;
	}
	
	@Override
	public Object getRowKey(Object object) {
		// TODO Auto-generated method stub
		return palabraClave;
	}

	@Override
	public Object getRowData(String rowKey) {
		// TODO Auto-generated method stub
		return this;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
