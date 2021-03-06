package ca.bcit.comp4655.rest.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class ServerUtil {
	
	private static Logger log = Logger.getLogger(ServerUtil.class );
	private static final String BUNDLE_NAME = "employee-service";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle( BUNDLE_NAME );

	public ServerUtil() {

	}

	public static String getString( String key )
	{
		try
		{
			if ( System.getenv( key ) != null )
			{
				return System.getenv( key );
			}
			return RESOURCE_BUNDLE.getString( key );
		}
		catch ( MissingResourceException e )
		{
			log.error( "Unable to find key [" + key + "] in " + BUNDLE_NAME );
			return '!' + key + '!';
		}
	}

	public static String getString( String key, Object... args )
	{
		try
		{
			if ( System.getenv( key ) != null )
			{
				return System.getenv( key );
			}
			String s = RESOURCE_BUNDLE.getString( key );
			return MessageFormat.format( s, args );
		}
		catch ( MissingResourceException e )
		{
			log.error( "Unable to find key [" + key + "] in " + BUNDLE_NAME );
			return '!' + key + '!';
		}
	}
}
