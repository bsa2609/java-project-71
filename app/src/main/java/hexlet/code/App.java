package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean usageHelpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private boolean versionInfoRequested;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    /**
     * Called from main().
     * @return - exit code
     */
    @Override
    public Integer call() throws Exception {
        try {
            String diffResult = Differ.generate(filepath1, filepath2, format);
            System.out.println(diffResult);
        } catch (Exception e) {
            System.out.println("File comparison error: " + e.getMessage());
        }

        return 0;
    }

    public static void main(String[] args) {
        //int exitCode = new CommandLine(new App()).execute(args);
        //System.exit(exitCode);
        new CommandLine(new App()).execute(args);
    }
}
