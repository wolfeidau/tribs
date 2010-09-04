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

package au.id.wolfe.tribs.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.id.wolfe.tribs.fixtures.WorkLogEntryFixture;

public class WorkLogEntryTest {

    @Test
    public void testWorkLogEntryEquals() {
        WorkLogEntry wlfirst = WorkLogEntryFixture.getWorkLogEntryWithData();
        WorkLogEntry wlsecond = WorkLogEntryFixture.getWorkLogEntryWithData();

        assertEquals(wlfirst, wlsecond);
    }

}
