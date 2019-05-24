package com.kitri.dto;

import java.util.Date;

public class RepBoard {	
	private int board_seq; //글번호
	private int parent_seq; //부모글번호:원글-0
	private String board_subject;//제목
	private String board_writer;//작성자
	private String board_contents;//내용
	private Date board_date;//작성일자
	private String board_password;//비번
	private int board_viewcount;//조회수
	private int level=1; //노드레벨
	public RepBoard() {
		super();
	}
	public RepBoard(int board_seq, int parent_seq, String board_subject, String board_writer, String board_contents,
			Date board_date, String board_password, int board_viewcount) {
		super();
		this.board_seq = board_seq;
		this.parent_seq = parent_seq;
		this.board_subject = board_subject;
		this.board_writer = board_writer;
		this.board_contents = board_contents;
		this.board_date = board_date;
		this.board_password = board_password;
		this.board_viewcount = board_viewcount;
	}
	/**
	 * 글쓰기용 생성자
	 * @param board_subject
	 * @param board_writer
	 * @param board_contents
	 * @param board_password
	 */
	public RepBoard(String board_subject, String board_writer, String board_contents, String board_password) {
		super();
		this.board_subject = board_subject;
		this.board_writer = board_writer;
		this.board_contents = board_contents;
		this.board_password = board_password;
	}
	/**
	 * 답글쓰기용 생성자
	 * @param parent_seq
	 * @param board_subject
	 * @param board_writer
	 * @param board_contents
	 * @param board_password
	 */
	public RepBoard(int parent_seq, String board_subject, String board_writer, String board_contents,
			String board_password) {
		super();
		this.parent_seq = parent_seq;
		this.board_subject = board_subject;
		this.board_writer = board_writer;
		this.board_contents = board_contents;
		this.board_password = board_password;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_contents() {
		return board_contents;
	}
	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_password() {
		return board_password;
	}
	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}
	public int getBoard_viewcount() {
		return board_viewcount;
	}
	public void setBoard_viewcount(int board_viewcount) {
		this.board_viewcount = board_viewcount;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "RepBoard [board_seq=" + board_seq + ", parent_seq=" + parent_seq + ", board_subject=" + board_subject
				+ ", board_writer=" + board_writer + ", board_contents=" + board_contents + ", board_date=" + board_date
				+ ", board_password=" + board_password + ", board_viewcount=" + board_viewcount + ", level=" + level
				+ "]";
	}
	
	
}
