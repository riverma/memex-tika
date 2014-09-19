package mil.darpa.memex.jpl.tika;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.CompositeParser;
import org.apache.tika.parser.Parser;

import mil.darpa.memex.jpl.tika.parser.*;

/**
 * 
 * @author rverma
 *
 */
public class App 
{

  private static HashMap<MediaType,Parser> parserMap = new HashMap<MediaType,Parser>();

  public static void main( String[] args ) throws IOException{
    //Define options
    @SuppressWarnings("static-access")
    Option input = OptionBuilder.withLongOpt("input_file")
    .withDescription("The input file you wish to parse").hasArg().withArgName("INPUT").create();
    @SuppressWarnings("static-access")
    Option output = OptionBuilder.withLongOpt("output_dir")
    .withDescription("The output directory you wish to write to").hasArg().withArgName("OUTPUT").create();
    Options opts = new Options();
    opts.addOption(input);
    opts.addOption(output);

    // create the parser
    GnuParser parser = new GnuParser();
    CommandLine line = null;
    HelpFormatter formatter = new HelpFormatter();
    try {
      if (args == null || args.length != 2) {
        formatter.printHelp( "PhoneNumberParser", opts, true );
        System.exit(0);;
      } else {
        // parse the command line arguments
        line = parser.parse( opts, args );
      }
    }
    catch( ParseException exp ) {
      // oops, something went wrong
      System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
      formatter.printHelp( "PhoneNumberParser", opts, true );
    }

    Tika tika = new Tika();
    
    if (line.hasOption("input_file")) {
      try {
        String parseResult = tika.parse((new File(line.getOptionValue("input_file"))));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {

    }
    // load parsers (TBD: optionally from arg list)
    parserMap.put(MediaType.TEXT_HTML, new PhoneParser());

    CompositeParser compositeParser = new CompositeParser();
    compositeParser.setParsers(parserMap);


  }
}
