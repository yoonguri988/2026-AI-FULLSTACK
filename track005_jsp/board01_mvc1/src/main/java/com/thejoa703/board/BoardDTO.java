package com.thejoa703.board;

public class BoardDTO {
	private int rownum;
	private int bno;
	private String btitle;
	private String bname;
	private String bdate;
	private int bhit;
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(int rownum, int bno, String btitle, String bname, String bdate, int bhit) {
		this.rownum = rownum;
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bhit = bhit;
	}


	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
}
