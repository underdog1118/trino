/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.hive.security;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;

public class TestLegacySecurityConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(LegacySecurityConfig.class)
                .setAllowAddColumn(false)
                .setAllowDropColumn(false)
                .setAllowDropTable(false)
                .setAllowRenameTable(false)
                .setAllowCommentTable(false)
                .setAllowCommentColumn(false)
                .setAllowRenameColumn(false));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("hive.allow-add-column", "true")
                .put("hive.allow-drop-column", "true")
                .put("hive.allow-drop-table", "true")
                .put("hive.allow-rename-table", "true")
                .put("hive.allow-comment-table", "true")
                .put("hive.allow-comment-column", "true")
                .put("hive.allow-rename-column", "true")
                .buildOrThrow();

        LegacySecurityConfig expected = new LegacySecurityConfig()
                .setAllowAddColumn(true)
                .setAllowDropColumn(true)
                .setAllowDropTable(true)
                .setAllowRenameTable(true)
                .setAllowCommentTable(true)
                .setAllowCommentColumn(true)
                .setAllowRenameColumn(true);

        assertFullMapping(properties, expected);
    }
}
