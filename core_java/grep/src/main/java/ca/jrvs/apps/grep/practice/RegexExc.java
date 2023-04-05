package ca.jrvs.apps.grep.practice;

public interface RegexExc {
    /**
     * return true if filename extension  is jpg or jpeg
     * @param filename
     * @return
     */
    public boolean matchJpeg(String filename);

    /**
     * return true it ip address is valid
     * @param ip
     * @return
     */
    public boolean matchIp(String ip);

    /**
     *returns true if line is empty(tab,whitespace,emptyline etc)
     * @param line
     * @return
     */
    public boolean isEmptyLine(String line);
}
