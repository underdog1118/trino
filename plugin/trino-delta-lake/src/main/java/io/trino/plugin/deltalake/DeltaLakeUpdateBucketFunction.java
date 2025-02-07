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
package io.trino.plugin.deltalake;

import io.airlift.slice.Slice;
import io.trino.spi.Page;
import io.trino.spi.block.SqlRow;
import io.trino.spi.connector.BucketFunction;

import static io.trino.spi.type.VarcharType.VARCHAR;

public class DeltaLakeUpdateBucketFunction
        implements BucketFunction
{
    private final int bucketCount;

    public DeltaLakeUpdateBucketFunction(int bucketCount)
    {
        this.bucketCount = bucketCount;
    }

    @Override
    public int getBucket(Page page, int position)
    {
        SqlRow row = page.getBlock(0).getObject(position, SqlRow.class);
        Slice value = VARCHAR.getSlice(row.getRawFieldBlock(0), row.getRawIndex()); // file path field of row ID
        return (value.hashCode() & Integer.MAX_VALUE) % bucketCount;
    }
}
