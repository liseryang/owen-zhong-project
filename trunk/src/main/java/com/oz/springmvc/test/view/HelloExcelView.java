package com.oz.springmvc.test.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.oz.springmvc.test.domain.Hello;

@Component("helloExcelView")
public class HelloExcelView extends AbstractJExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map paramMap,
			WritableWorkbook paramWritableWorkbook,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		WritableSheet sheet = paramWritableWorkbook.createSheet("first cell", 0);
		WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10); 
		arial10font.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat cellFormat = new WritableCellFormat (arial10font); 
		cellFormat.setShrinkToFit(true);
		sheet.addCell(new Label(0,0,"ID",cellFormat));
		sheet.addCell(new Label(1,0,"Booking No",cellFormat));
		List<Hello> list = (List<Hello>) paramMap.get("list");
		for( int i=0;i<list.size();i++){
			Hello hello = list.get(i);
			sheet.addCell(new Label(0,i+1,String.valueOf(hello.getId())));
			sheet.addCell(new Label(1,i+1,hello.getBookingNo()));
		}
	}

}
