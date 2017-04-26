/*
 * Projet: Datawings
 * SqlUtil
 * @author: DW
 * copyright 2007-2008
 * Version: V1.0 - 11 mars 10
 * Date creation: 11 mars 10
 */
package com.datawings.app.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * SqlUtil
 * @author: DW
 * <br>Projet: Datawings - Web
 * <br>copyright <b>2007-2008</b>
 * <br>Version: V1.0 - 11 mars 10
 * <br>Date creation: 11 mars 10
 */
public class SqlUtil {

	private SqlUtil() {		
	}
	
	public static void buildBetwInLikeSql(String val, String propertyName, StringBuffer sql, Object type){	
		val = val.replaceAll("'", "*");
		propertyName = "UPPER(TRIM("+propertyName+"))";
		boolean withOr = false;		
		StringBuffer chk = new StringBuffer("");		
		String[] values = values4buildBetwInLike(val, "*", chk);
		String likeOrNotLike = chk.toString().trim();
		if("LIKE".equals(likeOrNotLike)){
			for (int i = 0; i < values.length; i++){			
				if (withOr){
					sql.append(" OR ");
					withOr = false;
				}
				sql.append(propertyName + " LIKE '" + values[i].trim() + "'" );
				
				if (i != (values.length - 1)){
					sql.append(" OR ");
				}			
			}			
		}
		if("NOT LIKE".equals(likeOrNotLike)){
			for (int i = 0; i < values.length; i++){			
				if (withOr){
					sql.append(" AND ");
					withOr = false;
				}
				sql.append(propertyName + " NOT LIKE '" + values[i].trim() + "'" );
				
				if (i != (values.length - 1)){
					sql.append(" AND ");
				}			
			}			
		}		
	}	
	
	public static void buildBetwInLikeSqlConstant(String val, String propertyName, StringBuffer sql, Object type){
		if(propertyName.contains("'")){
			propertyName = propertyName.replaceAll("'", "");
		}		
		propertyName = "UPPER(TRIM('"+propertyName+"'))";
		boolean withOr = false;		
		StringBuffer chk = new StringBuffer("");		
		String[] values = values4buildBetwInLike(val, "*", chk);
		String likeOrNotLike = chk.toString().trim();
		if("LIKE".equals(likeOrNotLike)){
			for (int i = 0; i < values.length; i++){			
				if (withOr){
					sql.append(" OR ");
					withOr = false;
				}
				sql.append(propertyName + " LIKE '" + values[i].trim() + "'" );
				
				if (i != (values.length - 1)){
					sql.append(" OR ");
				}			
			}			
		}
		if("NOT LIKE".equals(likeOrNotLike)){
			for (int i = 0; i < values.length; i++){			
				if (withOr){
					sql.append(" AND ");
					withOr = false;
				}
				sql.append(propertyName + " NOT LIKE '" + values[i].trim() + "'" );
				
				if (i != (values.length - 1)){
					sql.append(" AND ");
				}			
			}			
		}		
	}
	
	private static String[] values4buildBetwInLike(String val, String joker, StringBuffer chk){	
		String[] result = null;
		String[] tmp = StringUtils.split(val, ":");
		if(tmp.length == 2){
			chk.append(tmp[0]);
			//String[] values = StringUtils.split(StringUtils.deleteWhitespace(tmp[1]), ";");	
			String[] values = StringUtils.split(tmp[1], ";");		
			List<String> list = new ArrayList<String>();		
			for(int i = 0; i < values.length; i++){			
				if (StringUtils.isBlank(values[i])){
					continue;
				}							
				if ("*".equals(joker)){					
					if (StringUtils.contains(values[i], "*")){							
						String s = StringUtils.replace(values[i], "*", "%");
						list.add(s);
					}
					continue;
				}					
			}					
			result = (String[]) list.toArray(new String[]{});	
		}			
		return result;
	}
	
	public static void buildBetwInLikeCrit(String val, String propertyName, Criteria crit){		
		StringBuffer sql = new StringBuffer();		
		buildBetwInSql(val, propertyName, sql);		
		crit.add(Restrictions.sqlRestriction(sql.toString()));		
	}
	
	public static void buildBetwInSql(String val, String propertyName, StringBuffer sql){
		String[] values = StringUtils.split(StringUtils.deleteWhitespace(val), ";");		
		for (int i = 0; i < values.length; i++){
			if (i == 0){
				sql.append(propertyName + " IN (");
			}			
			sql.append("'" + values[i].trim() + "'");			
			if (i != (values.length - 1)){
				sql.append(", ");
			}			
			if (i == (values.length - 1)){
				sql.append(")");
			}						
		}		
	}
	
	public static void buildDatesBetweenLikeSql(String propertyName, String dateFrom, String dateTo, StringBuffer sql){
			if (StringUtils.isNotBlank(dateFrom.trim()) && DateUtil.checkDateAsString(dateFrom.trim(), "dd/MM/yyyy")
					&& StringUtils.isNotBlank(dateTo.trim()) && DateUtil.checkDateAsString(dateTo.trim(), "dd/MM/yyyy")){
				sql.append(" " + propertyName  + " between " + "str_to_date('" + dateFrom.trim() + "', '%d/%m/%Y') " + " and str_to_date('" + dateTo.trim() + "', '%d/%m/%Y')");				
			}
			else if (StringUtils.isNotBlank(dateFrom.trim()) && DateUtil.checkDateAsString(dateFrom.trim(), "dd/MM/yyyy") && StringUtils.isBlank(dateTo.trim())){
				sql.append(" " + propertyName  + " >= " + "str_to_date('" + dateFrom.trim() + "', '%d/%m/%Y') ");
			}
			else if (StringUtils.isBlank(dateFrom.trim())  && StringUtils.isNotBlank(dateTo.trim()) && DateUtil.checkDateAsString(dateTo.trim(), "dd/MM/yyyy")){
				sql.append(" " + propertyName  + " <= " + "str_to_date('" + dateTo.trim() + "','%d/%m/%Y') ");
			}
			else{
				sql.append("");
			}
	}

	public static void buildDatesBetweenLikeCrit(String propertyName, String dateFrom, String dateTo, Criteria crit){
		StringBuffer sql = new StringBuffer();		
		buildDatesBetweenLikeSql(propertyName, dateFrom, dateTo, sql);		
		if (StringUtils.isNotBlank(sql.toString())){			
			crit.add(Restrictions.sqlRestriction(sql.toString()));			
		}
	}
}
