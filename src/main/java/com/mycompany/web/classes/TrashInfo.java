package com.mycompany.web.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательный класс для обмена информацией между Trash контроллером 
 * {@link com.mycompany.web.controller#TrashController} и представлением
 */
public class TrashInfo {
	
	// используется для записи/отображения значений при заполнении/отображении формы
	
	private List<BookOrderInfo> listBookOrderInfo;

	public List<BookOrderInfo> getListBookOrderInfo() {
		if (listBookOrderInfo == null)
			listBookOrderInfo = new ArrayList<>(); 
		return listBookOrderInfo;
	}

	public void setListBookOrderInfo(List<BookOrderInfo> listBookOrderInfo) {
		this.listBookOrderInfo = listBookOrderInfo;
	}

}
