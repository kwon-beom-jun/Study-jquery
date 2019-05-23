package com.kitri.dto;

import java.util.Date;

public class RepBoard {
	
//	BOARD_SEQ              NUMBER(10)  primary key, 
//	 PARENT_SEQ             NUMBER(10),  /** 원글인 경우:0, 답글인 경우: 부모 원글 번호 **/
//	 BOARD_SUBJECT        VARCHAR2(255),
//	 BOARD_WRITER          VARCHAR2(100),
//	 BOARD_CONTENTS     VARCHAR2(4000),
//	 BOARD_DATE            TIMESTAMP(6),
//	 BOARD_PASSWORD    VARCHAR2(20),
//	 BOARD_VIEWCOUNT    NUMBER(6)
	
	private int board_seq; 			// 글번호
	private int parent_seq; 		// 부모글번호 : 원글-0
	private String board_subject; 	// 제목
	private String board_writer; 	// 작성자
	private String board_contents; 	// 내용
	private Date board_date; 		// 작성일자
	private String board_password;	//비번
	private int board_viewcount; 	// 조회수
	
	public RepBoard() {
		super();
	}

	public RepBoard(int board_seq, int parent_seq, String board_subject, String board_writer, String board_contents,
			Date board_date, String board_password, int board_viewcount) {
		super();
		this.board_seq = board_seq; // DB에서 생성
		this.parent_seq = parent_seq; // DB에서 생성
		this.board_subject = board_subject;
		this.board_writer = board_writer;
		this.board_contents = board_contents;
		this.board_date = board_date; // DB생성
		this.board_password = board_password;
		this.board_viewcount = board_viewcount; // 글쓸때는 0으로 생성하게 만듬
	}

//	글쓰기용 생성자
	public RepBoard(String board_subject, String board_writer, String board_contents, String board_password) {
		super();
		this.board_subject = board_subject;
		this.board_writer = board_writer;
		this.board_contents = board_contents;
		this.board_password = board_password;
//		this.board_viewcount = board_viewcount; // 글쓸때는 0으로 생성하게 만듬
//		this.parent_seq = parent_seq; // DB에서 생성
//		this.board_seq = board_seq; // DB에서 생성
//		this.board_date = board_date; // DB생성

	}

	
//	답글쓰기용 생성자
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
	
	
	
	
	
	
}
