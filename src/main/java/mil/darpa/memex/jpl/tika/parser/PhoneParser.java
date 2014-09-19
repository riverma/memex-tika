package mil.darpa.memex.jpl.tika.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

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

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
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
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    System.out.println("Supported Dialing Regions: " + StringUtils.join(phoneUtil.getSupportedRegions(), ", "));
    System.out.println("Supported Global Network Calling Codes: " + StringUtils.join(phoneUtil.getSupportedGlobalNetworkCallingCodes(), ", "));
    try {
      PhoneNumber numberProto = phoneUtil.parse(parse, "US");
    } catch (NumberParseException e) {
      System.err.println("NumberParseException was thrown: " + e.toString());
    }

  }
}
