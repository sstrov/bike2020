package fscms.cmm.util.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import fscms.cmm.util.UploadHelper;

public class ExcelUtil {
	
	private static Logger logger = LogManager.getLogger(ExcelUtil.class);
	
	public static File toExcelFile(List<?> dataList, Class<?> voClass) throws IOException {
		return toExcelFile(dataList, voClass, "");
	}
		
	public static File toExcelFile(List<?> dataList, Class<?> voClass, String group) throws IOException {
		return toExcelFile(dataList, voClass, group, null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static File toExcelFile(List<?> dataList, Class<?> voClass, String group, String[] headers) throws IOException {
		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		Sheet sheet = workbook.createSheet();
		int rownum = 0;
		int cellnum = 0;
		
		// 테이블 헤더용 스타일
		CellStyle headStyle = workbook.createCellStyle();
		// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		// 배경색은 노란색입니다.
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		headStyle.setFont(font);

		// 데이터는 가운데 정렬합니다.
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle dateStyle = workbook.createCellStyle();
		
		List<java.lang.reflect.Field> fieldList = annotaionFieldList(voClass, TableList.class, true);
		List<java.lang.reflect.Field> visibleFieldList = new ArrayList();
		
		Row row = sheet.createRow(rownum++);
		
		Map<String, String> dateStyles = new HashMap();
		
		int idx = 0;
		for (java.lang.reflect.Field f : fieldList) {
			TableList listAnnotation = (TableList) f.getAnnotation(TableList.class);
			boolean isVisible = listAnnotation.visible();
			
			if (isVisible) {
				String[] groups = listAnnotation.group();
				if ((StringUtils.isNotEmpty(group)) && (ArrayUtils.isNotEmpty(groups)) && (!ArrayUtils.contains(groups, group))) {
					isVisible = false;
				}
			}
			
			if (isVisible) {
				String header;
				if (null != headers) {
					header = headers[(idx++)];
				} else {
					header = listAnnotation.caption();
					if (StringUtils.isEmpty(header)) {
						header = "";
					}
				}
				Cell cell = row.createCell(cellnum++);
				cell.setCellStyle(headStyle);
				cell.setCellValue(header);
				
				visibleFieldList.add(f);
				if (Date.class.isAssignableFrom(f.getType())) {
					String format = listAnnotation.format();
					if (StringUtils.isEmpty(format)) {
						format = "yyyy-mm-dd";
					}
					dateStyles.put(f.getName(), format);
				}
			}
		}
		
		Iterator itr = dataList.iterator();
		Object vo;
		
		while (itr.hasNext()) {
			vo = itr.next();
			row = sheet.createRow(rownum++);
			cellnum = 0;
			
			for (java.lang.reflect.Field field : visibleFieldList) {
				Cell cell = row.createCell(cellnum++);
				field.setAccessible(true);
				Object value = null;
				try {
					value = field.get(vo);
				} catch (IllegalAccessException e) {
					logger.error("{}", e);
				}
				
				if (value != null) {
					setCellValue(cell, field.getType(), value);
					
					if (Date.class.isAssignableFrom(field.getType())) {
						dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat(MapUtils.getString(dateStyles, field.getName())));
						cell.setCellStyle(dateStyle);
					}
				}
			}
		}
		
		String filePath = new UploadHelper().getAbsoluteUploadPath("excel", "");
		// 업로드디렉토리체크후 없으면 생성
		File dirPath = new File(filePath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
		File xlsxFile = new File(filePath + File.separator + RandomStringUtils.randomAlphanumeric(10) + ".xlsx");
		
		FileOutputStream out = new FileOutputStream(xlsxFile);
		workbook.write(out);
		out.close();
		workbook.close();
		
		return xlsxFile;
	}	
	
	private static void setCellValue(Cell cell, Class<?> type, Object value) {
		if (!isNumeric(type)) {
			if (Date.class.isAssignableFrom(type)) {
				cell.setCellValue((Date)value);
			} else if ((Boolean.class.equals(type)) || (Boolean.TYPE.equals(type))) {
				cell.setCellValue(((Boolean)value).booleanValue());
			} else {
				cell.setCellValue((String)value);
			}
		} else {
			try
			{
				Double dValue = Double.valueOf(Double.parseDouble(value.toString()));
				cell.setCellValue(dValue.doubleValue());
			}
			catch (NumberFormatException e) {
				logger.warn("NumberFormatException parsing a numeric value : " + value);
				cell.setCellValue((String)value);
			}
		}
	}
	
	private static boolean isNumeric(Class<?> type) {
		boolean isNumeric = false;
		if ((Integer.class.equals(type)) || (Integer.TYPE.equals(type)) || (Long.class.equals(type))
				|| (Long.TYPE.equals(type)) || (Short.class.equals(type)) || (Short.TYPE.equals(type))
				|| (Double.class.equals(type)) || (Double.TYPE.equals(type)) || (Float.class.equals(type))
				|| (Float.TYPE.equals(type)) || (BigDecimal.class.equals(type))) {
			
			isNumeric = true;
		}
		
		return isNumeric;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Field> annotaionFieldList(Class<?> voClass, Class<? extends Annotation> annotationClass, boolean isOrder) {
		List<Field> fieldList = new ArrayList();
		
		try {
			fieldList = searchAnnotaionField(fieldList, voClass, annotationClass);
			if (isOrder) {
				Collections.sort(fieldList, new Comparator<Field>() {
					@Override
					public int compare(Field first, Field second) {
						
						int firstOrder = getOrder(first);
						int secondOrder = getOrder(second);
						
						int result = -1;
						if (firstOrder > secondOrder) {
							result = 1;
						} else if (firstOrder == secondOrder) {
							result = 0;
						}
						return result;
					}
					
					private int getOrder(java.lang.reflect.Field first) {
						Annotation ac = first.getAnnotation(annotationClass);
						
						int order = 0;
						order = ((TableList)ac).order(); 
						
						return order;
					}
				});
			}
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("", e);
		}
		return fieldList;
	}
	
	private static <T> List<java.lang.reflect.Field> searchAnnotaionField(List<java.lang.reflect.Field> fieldList, Class<?> voClass, Class<? extends Annotation> annotationClass)
		throws InstantiationException, IllegalAccessException {
		
		List<java.lang.reflect.Field> annotationFieldList = fieldList;
		for (java.lang.reflect.Field field : voClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(annotationClass)) {
				boolean dupleCheck = true;
				for (java.lang.reflect.Field f : annotationFieldList) {
					if (f.getName().equals(field.getName())) {
						dupleCheck = false;
					}
				}
				if (dupleCheck) {
					annotationFieldList.add(field);
				}
			}
		}
		
		return annotationFieldList;
	}

}
