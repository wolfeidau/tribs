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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void testParseDateWithValidStrings() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2010, 0, 1, 0, 0, 0);

        Date expectedDate = cal.getTime();

        Date newDate = DateUtils.parseDate("2010-01-01",
                new String[] { "yyyy-MM-dd" });

        assertNotNull(newDate);
        assertEquals(expectedDate, newDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsDateParseIsThrowForDate() throws Exception {
        DateUtils.parseISO8601Date("crappydate");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsDateParseIsThrowForNullDate() throws Exception {
        DateUtils.parseISO8601Date(null);
    }
}
