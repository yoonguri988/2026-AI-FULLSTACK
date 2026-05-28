package com.thejoa703.board;

public class BoardDTO {
	private int rownum;
	private int bno;
	private String btitle;
	private String bname;
	private String bpass;
	private String bdate;
	private String bcontent;
	private String bip;
	private int bhit;
	
	public BoardDTO() {
		super();
	}

	//insert
	public BoardDTO(String bname, String bpass, String btitle, String bcontent, String bip) {
		this.bname = bname;
		this.bpass = bpass;
		this.btitle = btitle;
		this.bname = bname;
		this.bcontent = bcontent;
		this.bip = bip;
	}
	
	//update
	public BoardDTO(int bno, String bname, String bpass, String btitle, String bcontent) {
		this.bno = bno;
		this.bpass = bpass;
		this.btitle = btitle;
		this.bname = bname;
		this.bcontent = bcontent;
	}
	
	//delete
	public BoardDTO(int bno, String bpass) {
		this.bno = bno;
		this.bpass = bpass;
	}
	
	//select
	public BoardDTO(int bno, String btitle, String bname, String bdate, String bcontent,
			String bip, int bhit) {
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bcontent = bcontent;
		this.bip = bip;
		this.bhit = bhit;
	}

	// selectAll
	public BoardDTO(int rownum, int bno, String btitle, String bname, String bdate, String bcontent,
			String bip, int bhit) {
		this.rownum = rownum;
		this.bno = bno;
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bcontent = bcontent;
		this.bip = bip;
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

	public String getBpass() {
		return bpass;
	}

	public void setBpass(String bpass) {
		this.bpass = bpass;
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

	public String getBip() {
		return bip;
	}

	public void setBip(String bip) {
		this.bip = bip;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	@Override
	public String toString() {
		return "BoardDTO [rownum=" + rownum + ", bno=" + bno + ", btitle=" + btitle + ", bname=" + bname + ", bpass="
				+ bpass + ", bdate=" + bdate + ", bcontent=" + bcontent + ", bip=" + bip + ", bhit=" + bhit + "]";
	}


}
