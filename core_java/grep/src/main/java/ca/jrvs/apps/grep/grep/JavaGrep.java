package ca.jrvs.apps.grep.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

    /**
     * I0 Exception
     */

    void process() throws IOException;

    List<File> listFiles(String rootDir) throws IOException;

    /**
     * readLines
     */

    List<String> readLines(File inputFile) throws IOException;


    /**
     * check the pattren
     */
    boolean contiansPattern(String line);


    /**
     * write to the file
     */

    void writeToFile(List<String> lines)throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);
    String getRegex();
    void setRegex(String regex);
    String getOutFile();
    void setOutFile(String outFile);

}
