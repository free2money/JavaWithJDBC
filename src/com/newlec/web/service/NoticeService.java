package com.newlec.web.service;

import java.util.List;

import com.newlec.web.entity.Notice;

public interface NoticeService {
	// 첫화면 공지사항 목록
	List<Notice> getNoticeList();

	// 특정 페이지의 공지사항 목록
	List<Notice> getNoticeList(int page);

	// 특정 키워드가 포함된 공지사항 목록
	List<Notice> getNoticeList(String field, String query);

	List<Notice> getNoticeList(int page, String field, String query);

	// 현재 글
	Notice getNotice(int id);

	// 다음 글
	Notice getNextNotice(int id);

	// 이전 글
	Notice getPreviewNotice(int id);

	// 공지사항 목록 일괄삭제
	int deleteNotices(int[] ids);

	// 공지사항 목록 일괄공개
	int pubNotice(int[] ids);

	// 공지사항 글 등록
	int regNotice(Notice notice);

	// 공지사항 글 수정
	int updateNotice(Notice notice);

	// 공지사항 글 삭제
	int deleteNotice(int id);

}
