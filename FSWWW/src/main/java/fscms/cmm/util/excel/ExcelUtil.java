package fscms.cmm.util.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	
	private static Logger logger = LogManager.getLogger(ExcelUtil.class);
	
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
	public static List<Field> annotaionFieldList(Class<?> voClass, final Class<? extends Annotation> annotationClass, boolean isOrder) {
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
