/**
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・國際電氣通信基礎技術研究所]</i>
 *
 * @version 2010/01/26
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
public class Constants {

    public static final int MAX_VIEWER_CHANNEL_NUM = 128;
    public static final long CACHE_TIME = 30000;
    public static final int NUM_ITERS = 1337;
    public static final String ALL = "ALL";
    public static final char NEWLINE = '\n';
    public static final String FRAGMENT_SEP = " ...";
    public static final String DATE_FORMAT = "hh:mm:ss:SSS";
    public static final String DOUBLE_FORMAT = "#.###";
    public static final String DOUBLE_FORMAT_SIMPLE = "#.##";
    public static final int MAX_DATA_VIEWER_PTS = 2000;
    public static final char COMMA = ',';
    public static final String SEPARATOR = "/";
    public static final int NUM_RESULTS_PER_PAGE = 1024; // TODO:change this after we page results
    public static final int BUFFER_SIZE = 4096;
    public static final String ENGLISH_EN = "English";
    public static final String JAPANESE_EN = "Japanese";
    public static final String ENGLISH_LOCALE = "en";
    public static final String JAPANESE_LOCALE = "ja";
    public static final String SEX_MALE_EN = "Male";
    public static final String SEX_FEMALE_EN = "Female";
    public static final String OPTION_UNKNOWN_EN = "Unknown";
    public static final String OPTION_RIGHT_EN = "Right";
    public static final String OPTION_LEFT_EN = "Left";
    public static final String OPTION_BOTH_EN = "Both";
    public static final String PUBLIC_STATUS_EN = "Public";
    public static final String RESTRICTED_STATUS_EN = "Restricted";
    public static final String DISABLED_STATUS_EN = "Disabled";
    public static final String SHOW_ALL = "showall";
    public static final String RESTRICTED_CHARACTER_REGEX = "[^'\\\\\\/:*?<>|\"]*";
    public static final String RESTRICTED_CHARACTER_REGEX_ONE_OR_MORE = "[^'\\\\\\/:*?<>|\"]+";
}
