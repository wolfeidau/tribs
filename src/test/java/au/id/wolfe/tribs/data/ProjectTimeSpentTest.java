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

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ProjectTimeSpentTest {

    @Test
    public void testAdditionOfTime() {
        ProjectTimeSpent pts = new ProjectTimeSpent("Hello project", "HP", 100l);

        assertEquals(100l, pts.getTimespent().longValue());

        pts.addHours(100l);

        assertEquals(200l, pts.getTimespent().longValue());
    }

    @Test
    public void testProjectTimeSpentEquals() {
        ProjectTimeSpent pts1 = new ProjectTimeSpent("Hello project", "HP",
                100l);

        ProjectTimeSpent pts2 = new ProjectTimeSpent("Hello project", "HP",
                100l);

        assertEquals(pts1, pts2);
    }

}
