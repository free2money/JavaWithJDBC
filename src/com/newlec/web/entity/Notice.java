package com.newlec.web.entity;

import java.sql.Date;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String writer_id;
	private Date regdate;
//	private String files;
	private int hit;

	public Notice() {
	}

	// insert遂 持失切
	public Notice(String title, String content, String writer_id) {
		this.title = title;
		this.content = content;
		this.writer_id = writer_id;
	}

	// update遂 持失切.
	public Notice(int id, String title, String content, String writer_id, int hit) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer_id = writer_id;
	}

	// select 遂 持失切
	public Notice(int id, String title, String content, String writer_id, Date regdate, int hit) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer_id = writer_id;
		this.regdate = regdate;
		this.hit = hit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", writer_id=" + writer_id
				+ ", regdate=" + regdate + ", hit=" + hit + "]";
	}

}