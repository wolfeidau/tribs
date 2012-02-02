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

package au.id.wolfe.tribs.fixtures;

import au.id.wolfe.tribs.data.WorkLogEntry;

import java.util.Date;

public final class WorkLogEntryFixture {

    public static WorkLogEntry getWorkLogEntryWithData() {
        return new WorkLogEntry(1L, "markw", "Mark Wolfe", 100L, "BBB-001",
                "Some Description", new Date(), new Date());
    }

}
