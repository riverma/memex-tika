package mil.darpa.memex.jpl.tika.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * This Parser seeks to (1) de-obfuscate phone information from a given
 * text source and (2) parse and attain extended metadata on identified
 * phone numbers
 * 
 * @author rverma
 *
 */
public class PhoneParser extends AbstractParser {

    private static final Set<MediaType> SUPPORTED_TYPES =
        Collections.unmodifiableSet(new HashSet<MediaType>(Arrays.asList(
                MediaType.text("html"),
                MediaType.application("xhtml+xml"),
                MediaType.application("vnd.wap.xhtml+xml"),
                MediaType.application("x-asp"))));	
	
	public Set<MediaType> getSupportedTypes(ParseContext context) {
		return SUPPORTED_TYPES;
	}

	public void parse(InputStream stream, ContentHandler handler,
			Metadata metadata, ParseContext context) throws IOException,
			SAXException, TikaException {
		String content = IOUtils.toString(stream);
		
		// call USC ISI de-obfuscating code transformations
		// ...
		
		// call Google Phone Number extraction library
		// ...
		
	}

}