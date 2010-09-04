/**
 * Copyright (C) 2010 Mark Wolfe <mark.wolfe@wolfe.id.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.id.wolfe.tribs.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Date related utility routines.
 *
 */
public final class DateUtils {

    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Parses a string representing a date using the ISO8601 pattern.
     * 
     * @param dateString
     *            the date to parse, not null
     * @return the parsed date
     * @throws IllegalArgumentException
     *             if the date string or pattern array is null
     * @throws IllegalArgumentException
     *             if none of the date patterns were suitable
     */
    public static Date parseISO8601Date(String dateString) {
        return parseDate(dateString, new String[] { ISO8601_DATE_PATTERN });

    }

    /**
     * <p>
     * Parses a string representing a date by trying a variety of different
     * parsers.
     * </p>
     * 
     * <p>
     * The parse will try each parse pattern in turn. A parse is only deemed
     * sucessful if it parses the whole of the input string. If no parse
     * patterns match, a ParseException is thrown.
     * </p>
     * 
     * @param str
     *            the date to parse, not null
     * @param parsePatterns
     *            the date format patterns to use, see SimpleDateFormat, not
     *            null
     * @return the parsed date
     * @throws IllegalArgumentException
     *             if the date string or pattern array is null
     * @throws IllegalArgumentException
     *             if none of the date patterns were suitable
     */
    public static Date parseDate(String str, String[] parsePatterns) {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException(
                    "Date and Patterns must not be null");
        }

        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);
        for (int i = 0; i < parsePatterns.length; i++) {
            if (i == 0) {
                parser = new SimpleDateFormat(parsePatterns[0]);
            } else {
                parser.applyPattern(parsePatterns[i]);
            }
            pos.setIndex(0);
            Date date = parser.parse(str, pos);
            if (date != null && pos.getIndex() == str.length()) {
                return date;
            }
        }
        throw new IllegalArgumentException("Unable to parse the date: " + str);
    }

}
