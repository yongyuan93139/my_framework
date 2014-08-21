package com.lu.xml.xstream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import com.lu.consts.DateConsts;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateConverter implements Converter
{

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class clazz)
	{
		return (Date.class).equals(clazz);
	}

	@Override
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context)
	{
		Date date = (Date) object;
		SimpleDateFormat format = new SimpleDateFormat(DateConsts.YEAR_MON_DAY);
		writer.setValue(format.format(date));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
	{
		String dateStr = reader.getValue();
		Date date = null;
		try
		{
			date = DateUtils.parseDate(dateStr, DateConsts.DATE_FORMAT_PATTERNS);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

}
