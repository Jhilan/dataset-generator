package com.jhilan;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.jhilan.exception.ConfigValidationException;

class Parser {
    private static final String CONFIG_FILE_PATH_OPTION = "config";
    private CommandLine commandLine;

    Parser(final String[] args) {
        parseAndValidate(args);
    }

    String getConfigFilePath() {
        return commandLine.getOptionValue(CONFIG_FILE_PATH_OPTION);
    }

    //---- private -----
    private void parseAndValidate(final String[] args) {
        try {
            final CommandLineParser parser = new DefaultParser();
            final Options options = getCliOptions();
            commandLine = parser.parse(options, args);

            if (!commandLine.hasOption(CONFIG_FILE_PATH_OPTION)) {
                final HelpFormatter help = new HelpFormatter();
                help.printHelp("java -jar dataset_generator.jar -config </path/to/config.yml>", options);
                System.exit(0);
            }
        } catch (final ParseException ex) {
            throw new ConfigValidationException("problem parsing args.", ex);
        }
    }

    private static Options getCliOptions() {
        final Options options = new Options();
        final Option configFilePath = new Option(CONFIG_FILE_PATH_OPTION, true, "Yaml file configuration path.");
        options.addOption(configFilePath);
        return options;
    }
}
