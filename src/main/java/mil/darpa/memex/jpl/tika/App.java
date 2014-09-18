package mil.darpa.memex.jpl.tika;

import java.util.HashMap;

import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.CompositeParser;
import org.apache.tika.parser.Parser;
import mil.darpa.memex.jpl.tika.parser.*;

public class App 
{
	
	private static HashMap<MediaType,Parser> parserMap = new HashMap<MediaType,Parser>();
	
    public static void main( String[] args )
    {
    	// load parsers (TBD: optionally from arg list)
    	parserMap.put(MediaType.TEXT_HTML, new PhoneParser());
    	
        CompositeParser compositeParser = new CompositeParser();
        compositeParser.setParsers(parserMap);
        
        
    }
}
