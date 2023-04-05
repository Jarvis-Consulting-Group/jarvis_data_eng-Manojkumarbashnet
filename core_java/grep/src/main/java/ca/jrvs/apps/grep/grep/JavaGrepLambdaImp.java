package ca.jrvs.apps.grep.grep;

import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {
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
    @Override
    public List<String> readLines(File inputfile) {
        List<String> lines=new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(inputfile)))) {
            lines=stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public List<File> listFiles(String rootDir) {

        List<File> lines;
        try (Stream<Path> walk = Files.walk(Paths.get(rootDir))) {

            lines = walk.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            lines.forEach(System.out::println);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
return lines;

    }
}