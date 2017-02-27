package netgloo.models;

public class HistoryRecordImmitation {
	
	public Long historyRecordId;
	
	public String imeRestorana;
	
	public String datumPosjete;
	
	public String hrana;
	
	public String pice;
	
	public int ocjenaRestorana;
	
	public int ocjenaHrane;
	
	public int ocjenaUsluge;

	public String getImeRestorana() {
		return imeRestorana;
	}

	public void setImeRestorana(String imeRestorana) {
		this.imeRestorana = imeRestorana;
	}

	
	

	public Long getHistoryRecordId() {
		return historyRecordId;
	}

	public void setHistoryRecordId(Long historyRecordId) {
		this.historyRecordId = historyRecordId;
	}

	public String getDatumPosjete() {
		return datumPosjete;
	}

	public void setDatumPosjete(String datumPosjete) {
		this.datumPosjete = datumPosjete;
	}

	public String getHrana() {
		return hrana;
	}

	public void setHrana(String hrana) {
		this.hrana = hrana;
	}

	public String getPice() {
		return pice;
	}

	public void setPice(String pice) {
		this.pice = pice;
	}

	public int getOcjenaRestorana() {
		return ocjenaRestorana;
	}

	public void setOcjenaRestorana(int ocjenaRestorana) {
		this.ocjenaRestorana = ocjenaRestorana;
	}

	public int getOcjenaHrane() {
		return ocjenaHrane;
	}

	public void setOcjenaHrane(int ocjenaHrane) {
		this.ocjenaHrane = ocjenaHrane;
	}

	public int getOcjenaUsluge() {
		return ocjenaUsluge;
	}

	public void setOcjenaUsluge(int ocjenaUsluge) {
		this.ocjenaUsluge = ocjenaUsluge;
	}

	public HistoryRecordImmitation(Long historyRecordId,String imeRestorana, String datumPosjete,
			String hrana, String pice, int ocjenaRestorana, int ocjenaHrane, int ocjenaUsluge) {
		super();
		this.imeRestorana = imeRestorana;
		this.datumPosjete = datumPosjete;
		this.hrana = hrana;
		this.pice = pice;
		this.ocjenaRestorana = ocjenaRestorana;
		this.ocjenaHrane = ocjenaHrane;
		this.ocjenaUsluge = ocjenaUsluge;
		this.historyRecordId=historyRecordId;
	}

	public HistoryRecordImmitation() {
		super();
	}
	
	
	

}
