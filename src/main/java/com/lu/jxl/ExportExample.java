package com.lu.jxl;

import java.io.File;
import jxl.CellView;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportExample {
	public static void main(String[] args) {
		export();
	}
	
	private static void export(){
		File file = new File("export.xls");
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("fstSheet", 0);
			sheet.setColumnView(1, 16);
			//表格属性设置
			SheetSettings settings = sheet.getSettings();
			//settings.setVerticalFreeze(0);//第一列冻结
			
			//sheet.mergeCells(0, 1, 2, 3);//合并单元格 列 行 列 行
			WritableCellFormat cellTitleFmt = getCellContetFormat(false);
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12,
				       WritableFont.NO_BOLD, false,
				       UnderlineStyle.NO_UNDERLINE);
			cellTitleFmt.setFont(font);
			//sheet.mergeCells(0,0,5,0);//(列，行，列，行)  
			
			//			Label titleLabel = new Label(0, 0, " 采暖市场部收入、成本、利润明细表 ", cellTitleFmt); //第1列，第0行
			//			sheet.addCell(titleLabel);
			
			WritableCellFormat cellHeaderFmt = getCellHeaderFormat();
			
			String[] headArr = {"姓名","身份证号","性别","年龄","收入","爱好"};
			//设置自动调整列宽 
			CellView cellView = new CellView();
			cellView.setAutosize(true); //设置自动大小  
			sheet.setColumnView(1, cellView);//第1列（从0列起）根据内容自动设置列宽  
			cellView.setSize(40);
			sheet.setColumnView(5, cellView);
			for(int i = 0; i < headArr.length; i++){
				sheet.addCell(new Label(i, 1, headArr[i],cellHeaderFmt));
			}
			
			WritableCellFormat noWrapCellContentFmt = getCellContetFormat(false);
			WritableCellFormat wrapCellContentFmt = getCellContetFormat(true);
			
			//			int count = 2;//从第二行 业务数据
			//			int j = 1;//第j列
			//sheet.addCell(new Label(j++,count,"lushi",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"140122165214214120",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"女",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"26",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"30.6",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"打球，大姐，打银行，，读书，打卡看到有sfwe是否是热伤风士大夫四分卫是非法是否",wrapCellContentFmt));
			
			//			count++;
			//			j=0;
			//			sheet.addCell(new Label(j++,count,"lushi",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"140122165214214120444",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"女",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"26",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"30.6",noWrapCellContentFmt));
			//			sheet.addCell(new Label(j++,count,"打球，大姐，打银行，，读书，打卡看到有sfwe是否是热伤风士色色认为色弱服务而盛大富翁粉色认为色范围大夫四分卫是非法是否",wrapCellContentFmt));
			
			workbook.write();
			workbook.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static WritableCellFormat getCellContetFormat(boolean isWrap) throws WriteException{
		WritableCellFormat normalFormat = new WritableCellFormat();
		
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
			       WritableFont.NO_BOLD, false,
			       UnderlineStyle.NO_UNDERLINE);
		normalFormat.setFont(font);
		 normalFormat.setBackground(jxl.format.Colour.WHITE);
		 //normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,jxl.format.Colour.GRAY_25);
		 normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN,jxl.format.Colour.BLACK); // 边框 
		 normalFormat.setAlignment(Alignment.CENTRE);//居中
		 if(isWrap){
			 normalFormat.setWrap(isWrap);
		 }
		 return normalFormat;
		 
	}
	
	private static WritableCellFormat getCellHeaderFormat() throws WriteException{
		WritableCellFormat normalFormat = getCellContetFormat(false);
		
		normalFormat.setBackground(Colour.GRAY_25);//背景灰色
		normalFormat.setBackground(Colour.LIGHT_TURQUOISE);
		 return normalFormat;
		 
	}
}
