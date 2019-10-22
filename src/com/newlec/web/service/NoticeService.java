package com.newlec.web.service;

import java.util.List;

import com.newlec.web.entity.Notice;

public interface NoticeService {
	// ùȭ�� �������� ���
	List<Notice> getNoticeList();

	// Ư�� �������� �������� ���
	List<Notice> getNoticeList(int page);

	// Ư�� Ű���尡 ���Ե� �������� ���
	List<Notice> getNoticeList(String field, String query);

	List<Notice> getNoticeList(int page, String field, String query);

	// ���� ��
	Notice getNotice(int id);

	// ���� ��
	Notice getNextNotice(int id);

	// ���� ��
	Notice getPreviewNotice(int id);

	// �������� ��� �ϰ�����
	int deleteNotices(int[] ids);

	// �������� ��� �ϰ�����
	int pubNotice(int[] ids);

	// �������� �� ���
	int regNotice(Notice notice);

	// �������� �� ����
	int updateNotice(Notice notice);

	// �������� �� ����
	int deleteNotice(int id);

}
