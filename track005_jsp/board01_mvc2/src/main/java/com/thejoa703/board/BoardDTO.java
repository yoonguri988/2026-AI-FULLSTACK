package com.thejoa703.board;

public class BoardDTO {
	private int rownum;
	private int bno;
	private String btitle;
	private String bname;
	private String bdate;
	private String bcontent;
	private int bhit;
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(int bno, String btitle, String bname, String bcontent) {
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bcontent = bcontent;
	}
	
	public BoardDTO(int bno, String btitle, String bname, String bcontent, int bhit) {
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bcontent = bcontent;
		this.bhit = bhit;
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
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	@Override
	public String toString() {
		return "BoardDTO [rownum=" + rownum + ", bno=" + bno + ", btitle=" + btitle + ", bname=" + bname + ", bdate="
				+ bdate + ", bcontent=" + bcontent + ", bhit=" + bhit + "]";
	}
}
