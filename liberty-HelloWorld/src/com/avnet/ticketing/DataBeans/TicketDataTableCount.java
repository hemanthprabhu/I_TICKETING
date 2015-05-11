package com.avnet.ticketing.DataBeans;

import java.io.Serializable;
import java.util.List;

public class TicketDataTableCount implements Serializable {
	private List<TicketDataTableBean> listofTickets;
	private int recordsFiltered;
	public List<TicketDataTableBean> getListofTickets() {
		return listofTickets;
	}
	public void setListofTickets(List<TicketDataTableBean> listofTickets) {
		this.listofTickets = listofTickets;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	@Override
	public String toString() {
		return "TicketDataTableCount [listofTickets=" + listofTickets
				+ ", recordsFiltered=" + recordsFiltered + "]";
	}
	
	
	

}
