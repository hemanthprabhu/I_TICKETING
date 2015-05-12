package com.avnet.ticketing.DataBeans;
import java.io.Serializable;

import org.json.simple.JSONObject;

public class DataTableBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7613170478103120868L;
	int draw;
	int recordsTotal;
	int recordsFiltered;
	private JSONObject responseMessage;
	private Object data;


	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {
		if(data==null)
		{
			data=new Object();
		}
		this.data = data;			
	}

	public JSONObject getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(JSONObject responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "DataTableBean [draw=" + draw + ", recordsTotal=" + recordsTotal
				+ ", recordsFiltered=" + recordsFiltered + ", responseMessage="
				+ responseMessage + ", data=" + data + "]";
	}




} 