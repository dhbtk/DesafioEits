/**
 * 
 */
package org.directwebremoting.convert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import org.directwebremoting.ConversionException;
import org.directwebremoting.extend.AbstractConverter;
import org.directwebremoting.extend.InboundVariable;
import org.directwebremoting.extend.NonNestedOutboundVariable;
import org.directwebremoting.extend.OutboundContext;
import org.directwebremoting.extend.OutboundVariable;
import org.directwebremoting.extend.ProtocolConstants;

/**
 * Conversor para LocalDate, LocalTime, e LocalDateTime
 * 
 * Não trata LocalTime bem: ele só é tratado na entrada se for uma data
 * @author diana
 *
 */
public class LocalDateTimeConverter extends AbstractConverter
{

	/* (non-Javadoc)
	 * @see org.directwebremoting.extend.Converter#convertInbound(java.lang.Class, org.directwebremoting.extend.InboundVariable)
	 */
	@Override
	public Object convertInbound(Class<?> paramType, InboundVariable data) throws ConversionException
	{
		if(data.isNull())
		{
			return null;
		}
		
		String val = data.getValue();
		
		if(val.trim().equals(ProtocolConstants.INBOUND_NULL))
		{
			return null;
		}
		
		try
		{
			long seconds = 0;
			if(val.length() > 0)
			{
				seconds = Long.parseLong(val)/1000;
			}
			LocalDateTime date = LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC);
			if(paramType == LocalDate.class)
			{
				return date.toLocalDate();
			}
			else if(paramType == LocalTime.class)
			{
				return date.toLocalTime();
			}
			else
			{
				throw new ConversionException(paramType);
			}
		}
		catch(ConversionException ex)
		{
			throw ex;
		}
		catch(Exception ex)
		{
			throw new ConversionException(paramType, ex);
		}
	}

	/* (non-Javadoc)
	 * @see org.directwebremoting.extend.Converter#convertOutbound(java.lang.Object, org.directwebremoting.extend.OutboundContext)
	 */
	@Override
	public OutboundVariable convertOutbound(Object data, OutboundContext outctx) throws ConversionException
	{
		long ms;
		LocalDateTime dt;
		if(data instanceof LocalDateTime)
		{
			dt = (LocalDateTime) data;
		}
		else if(data instanceof LocalDate)
		{
			dt = LocalDateTime.of((LocalDate) data, LocalTime.MIDNIGHT); 
		}
		else if(data instanceof LocalTime)
		{
			dt = LocalDateTime.of(LocalDate.now(), (LocalTime) data);
		}
		else
		{
			throw new ConversionException(data.getClass());
		}
		ms = dt.toEpochSecond(ZoneOffset.UTC)*1000;
		
		return new NonNestedOutboundVariable("new Date(" + ms + ")");
	}

}
