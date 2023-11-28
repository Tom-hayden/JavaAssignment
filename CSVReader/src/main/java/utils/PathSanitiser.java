package utils;

import org.apache.commons.text.StringEscapeUtils;

public class PathSanitiser {

    public static String sanitisePath(String path) {
        //This regex matches " or ' at the beginning or end of the path.
        String result = path.replaceAll("^[\"']|[\"']$", "");
        return StringEscapeUtils.escapeJava(result);
    }
}
