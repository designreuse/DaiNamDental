package com.datawings.app.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtil {

	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void initSimplePropertyBean(Object bean) {

		PropertyDescriptor descriptors[] = PropertyUtils .getPropertyDescriptors(bean);

		for (int i = 0; i < descriptors.length; i++) {

			String name = descriptors[i].getName();

			if (descriptors[i].getWriteMethod() != null) {
				try {
					Class t = PropertyUtils.getPropertyType(bean, name);

					if ("String".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, "");
					} else if ("Character".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Character(' '));
					} else if ("Byte".equalsIgnoreCase(t.getSimpleName())) {
						Integer ent = new Integer(0);
						PropertyUtils.setSimpleProperty(bean, name, ent.byteValue());
					} else if ("Short".equalsIgnoreCase(t.getSimpleName())) {
						Integer ent = new Integer(0);
						PropertyUtils.setSimpleProperty(bean, name, ent.shortValue());
					} else if ("Integer".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Integer(0));
					} else if ("Long".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Long(0));
					} else if ("Float".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Float(0));
					} else if ("Double".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Double(0));
					} else if ("Date".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Date());
					} else if ("Boolean".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, new Boolean(false));
					} else if ("char".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, ' ');
					} else if ("int".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, 0);
					} else if ("long".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, 0);
					} else if ("float".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, 0.0);
					} else if ("double".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, 0.0);
					} else if ("boolean".equalsIgnoreCase(t.getSimpleName())) {
						PropertyUtils.setSimpleProperty(bean, name, false);
					} else {
						//System.out.println("property [" + name + "] de Type [" + t.getSimpleName() + "]" + " n est pas initialise");
					}
				} catch (IllegalAccessException e) {
					System.out.println("IllegalAccessException error [" + e.getMessage() + "]");
				} catch (NoSuchMethodException e) {
					System.out.println("NoSuchMethodException error [" + e.getMessage() + "]");
				} catch (InvocationTargetException e) {
					System.out.println("InvocationTargetException error [" + e.getMessage() + "]");
				}
			}
		}
	}

	public static void loadSimpleValues(Object bean, Object[] values) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		Field[] fields = bean.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			PropertyUtils.setSimpleProperty(bean, fields[i].getName(),values[i]);
		}
	}

	public static String toString(Object bean) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		Field[] fields = bean.getClass().getDeclaredFields();

		String result = bean.toString() + "\n";

		for (int i = 0; i < fields.length; i++) {

			result += fields[i].getName()
					+ "=["
					+ PropertyUtils
							.getSimpleProperty(bean, fields[i].getName())
					+ "]\n";

		}

		return result;
	}

	public static String bean2String(Object bean)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		StringBuilder rs = new StringBuilder();

		Field[] fields = bean.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			rs.append(fields[i].getName());
			rs.append("=");
			rs.append(PropertyUtils
					.getSimpleProperty(bean, fields[i].getName()).toString()
					.trim());
			if (i < fields.length - 1) {
				rs.append(";");
			}
		}
		return rs.toString();
	}

	@SuppressWarnings("rawtypes")
	public static Object string2Bean(String str, Object bean) {

		String[] ob = str.split(";");
		Field[] fields = bean.getClass().getDeclaredFields();
		if (ob.length != fields.length) {
			new Object();
		}
		try {
			for (int i = 0; i < fields.length; i++) {
				Class t = PropertyUtils.getPropertyType(bean,
						fields[i].getName());
				String name = fields[i].getName();
				if ("String".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name,
							ob[i].substring(name.length() + 1, ob[i].length()));
				} else if ("Character".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name,
							ob[i].substring(name.length() + 1, ob[i].length()));
				} else if ("Byte".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Byte
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("Short".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Short
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("Integer".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Integer
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("Long".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Long
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("Float".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Float
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("Double".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Double
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("Date".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(
							bean,
							name,
							DateUtil.string2Date(
									ob[i].substring(name.length() + 1,
											ob[i].length()), "dd/MM/yyyy"));
				} else if ("Boolean".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Boolean
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("char".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name,
							ob[i].substring(name.length() + 1, ob[i].length()));
				} else if ("int".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Integer
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("long".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Long
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("float".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Float
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("double".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Double
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else if ("boolean".equalsIgnoreCase(t.getSimpleName())) {
					PropertyUtils.setSimpleProperty(bean, name, Boolean
							.valueOf(ob[i].substring(name.length() + 1,
									ob[i].length())));
				} else {
					System.out
							.println("property [" + name + "] de Type ["
									+ t.getSimpleName() + "]"
									+ " n est pas initialise");
				}
			}
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException error ["
					+ e.getMessage() + "]");
		} catch (NoSuchMethodException e) {
			System.out.println("NoSuchMethodException error [" + e.getMessage()
					+ "]");
		} catch (InvocationTargetException e) {
			System.out.println("InvocationTargetException error ["
					+ e.getMessage() + "]");
		}
		return bean;
	}

	@SuppressWarnings("rawtypes")
	public static void initNotNullPropertyBean(Object bean) {

		PropertyDescriptor descriptors[] = PropertyUtils.getPropertyDescriptors(bean);

		for (int i = 0; i < descriptors.length; i++) {

			String name = descriptors[i].getName();	
			
			if (descriptors[i].getWriteMethod() != null) {
				try {
					Class t = PropertyUtils.getPropertyType(bean, name);
					Object obj = PropertyUtils.getProperty(bean, name);
				
					if ("String".equalsIgnoreCase(t.getSimpleName()) && obj == null) {						
						PropertyUtils.setSimpleProperty(bean, name, "");
					} else if ("Character".equalsIgnoreCase(t.getSimpleName() ) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name,
								new Character(' '));
					} else if ("Byte".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						Integer ent = new Integer(0);
						PropertyUtils.setSimpleProperty(bean, name,
								ent.byteValue());
					} else if ("Short".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						Integer ent = new Integer(0);
						PropertyUtils.setSimpleProperty(bean, name,
								ent.shortValue());
					} else if ("Integer".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name,
								new Integer(0));
					} else if ("Long".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils
								.setSimpleProperty(bean, name, new Long(0));
					} else if ("Float".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name,
								new Float(0));
					} else if ("Double".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, new Double(
								0));
					} else if ("Date".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, new Date());
					} else if ("Boolean".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name,
								new Boolean(false));
					} else if ("char".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, ' ');
					} else if ("int".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, 0);
					} else if ("long".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, 0);
					} else if ("float".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, 0.0);
					} else if ("double".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, 0.0);
					} else if ("boolean".equalsIgnoreCase(t.getSimpleName()) && obj == null) {
						PropertyUtils.setSimpleProperty(bean, name, false);
					} else {
						System.out.println("property [" + name + "] de Type ["
								+ t.getSimpleName() + "]"
								+ " n est pas initialise");
					}
				} catch (IllegalAccessException e) {
					System.out.println("IllegalAccessException error ["
							+ e.getMessage() + "]");
				} catch (NoSuchMethodException e) {
					System.out.println("NoSuchMethodException error ["
							+ e.getMessage() + "]");
				} catch (InvocationTargetException e) {
					System.out.println("InvocationTargetException error ["
							+ e.getMessage() + "]");
				}
			}

		}

	}
	
	public static void initSimplePropertyBean4Sbr(Object bean){

		PropertyDescriptor descriptors[] = PropertyUtils.getPropertyDescriptors(bean);

		for (int i = 0; i < descriptors.length; i++) {

			String name = descriptors[i].getName();

			if (descriptors[i].getWriteMethod() != null){
			   	try{
			   		Class<?> t = PropertyUtils.getPropertyType(bean, name);

				 	if ("String".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, "");
					}
					else
					if ("Character".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, new Character(' '));
					}
					else
					if ("Byte".equalsIgnoreCase(t.getSimpleName())){
						Integer ent = new Integer(0);
						PropertyUtils.setSimpleProperty(bean, name, ent.byteValue());
					}
					else
					if ("Short".equalsIgnoreCase(t.getSimpleName())){
						Integer ent = new Integer(0);
						PropertyUtils.setSimpleProperty(bean, name, ent.shortValue());
					}
					else
					if ("Integer".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, new Integer(0));
					}
					else
					if ("Long".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, new Long(0));
					}
					else
					if ("Float".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, new Float(0));
					}
					else
					if ("Double".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, new Double(0));
					}
					else
					if ("Date".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, new Date());
					}
					else
					if ("Boolean".equalsIgnoreCase(t.getSimpleName())){
							PropertyUtils.setSimpleProperty(bean, name, new Boolean(false));
						}
					else
					if ("char".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, ' ');
					}
					else
					if ("int".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, 0);
					}
					else
					if ("long".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, 0);
					}
					else
					if ("float".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, 0.0);
					}
					else
					if ("double".equalsIgnoreCase(t.getSimpleName())){
							PropertyUtils.setSimpleProperty(bean, name, 0.0);
					}
					else
					if ("boolean".equalsIgnoreCase(t.getSimpleName())){
						PropertyUtils.setSimpleProperty(bean, name, false);
					}
					else {
						Object beans = PropertyUtils.getSimpleProperty(bean, name);
						if(beans == null){
							try {
								beans = t.newInstance();
								initSimplePropertyBean4Sbr(beans);
								PropertyUtils.setSimpleProperty(bean, name, beans);
							} catch (InstantiationException e) {
								e.printStackTrace();
							}
						}
					}
				}
				catch (IllegalAccessException e){
					System.out.println("IllegalAccessException error [" + e.getMessage() + "]");
		 		}
				catch (NoSuchMethodException e) {
					System.out.println("NoSuchMethodException error [" + e.getMessage() + "]");
				}
				catch (InvocationTargetException e) {
					System.out.println("InvocationTargetException error [" + e.getMessage() + "]");
				}
			}
		}
	}
}
