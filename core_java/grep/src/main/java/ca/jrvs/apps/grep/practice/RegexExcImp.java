package ca.jrvs.apps.grep.practice;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexExcImp implements RegexExc {
    public static void main(String[] args) {
            RegexExcImp regex=new RegexExcImp();
        System.out.println(regex.matchJpeg("getfile.jpg"));
        System.out.println(  regex.matchIp("0.0.0.0"));
        System.out.println(regex.isEmptyLine("demo"));


    }

    @Override
    public boolean matchJpeg(String filename) {
        Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)", Pattern.CASE_INSENSITIVE);
Matcher match =  pattern.matcher(filename);


            return match.matches();


    }

    @Override
    public boolean matchIp(String ip) {
        String regex2 = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return ip.matches(regex2);
    }

    @Override
    public boolean isEmptyLine(String line) {
        String regex3= "^[ \\t\\n]*$";
        return line.matches(regex3);
    }
}
