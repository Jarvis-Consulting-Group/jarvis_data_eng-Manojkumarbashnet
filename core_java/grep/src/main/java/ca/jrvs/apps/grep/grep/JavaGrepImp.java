package ca.jrvs.apps.grep.grep;


import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JavaGrepImp implements JavaGrep {
    static final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE:java grep regex roothPtah outfile");


        }

        BasicConfigurator.configure();
        JavaGrepImp javaGrepIp = new JavaGrepImp();
        javaGrepIp.setRegex(args[0]);
        javaGrepIp.setRootPath(args[1]);
        javaGrepIp.setOutFile(args[2]);
    try {
            javaGrepIp.process();
        } catch (Exception e) {
            JavaGrepImp.logger.error("unable to process", e);
        }

    }

    private String regex;

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public void process() throws IOException {

        List<String> matchedLines = new ArrayList<>();
        List<File> filesInDir=listFiles(getRootPath());
        for (File file : filesInDir) {
            for (String line:readLines(file)) {
                if (contiansPattern(line)) {
                    matchedLines.add(line);
                }

            }

        }
        writeToFile(matchedLines);
        logger.info("file processing complete");


    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> files = new ArrayList<>();
        File root = new File(rootDir);
        File[] list = root.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) {
                    files.addAll(listFiles(f.getAbsolutePath()));
                } else {
                    files.add(f);
                }
            }

        }
        return files;


    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<>();
        String line;
        try (BufferedReader read = new BufferedReader(new FileReader(inputFile))) {
            while ((line = read.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;

        return lines;
    }

    @Override
    public boolean contiansPattern(String line) {
        return line.matches(getRegex());
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(getOutFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String line:lines){
                bufferedWriter.write(line+System.lineSeparator());
            }
            bufferedWriter.close();
        }

        catch (IOException e) {
            logger.error("File not found",e);
        }
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    private String rootPath;

    private String outFile;


}
