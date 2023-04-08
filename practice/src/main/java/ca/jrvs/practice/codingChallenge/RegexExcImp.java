package ca.jrvs.practice.codingChallenge;

import java.util.regex.Pattern;

class RegexExcImp {

    public void main(String[] args) {
        regexEx file= new regexEx();
    }
    public class regexEx implements RegexExc {



        @Override
        public boolean matchJpeg(String filename) {
            Pattern pattern = Pattern.compile(".*\\.(jpg)", Pattern.CASE_INSENSITIVE);
            System.out.println(pattern.matcher(filename));
            return false;

        }
    }
}