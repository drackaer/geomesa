/*
 * Copyright 2015 Commonwealth Computer Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.locationtech.geomesa.core.iterators

import java.nio.ByteBuffer
import java.util
import java.util.{Collection => jCollection}

import org.apache.accumulo.core.data.{Range => AccRange, _}
import org.apache.accumulo.core.iterators.{IteratorEnvironment, SortedKeyValueIterator}
import org.apache.hadoop.io.Text
import org.geotools.feature.simple.SimpleFeatureBuilder
import org.locationtech.geomesa.core.iterators.RowSkippingIterator._
import org.locationtech.geomesa.feature.{FeatureEncoding, SimpleFeatureEncoder}
import org.locationtech.geomesa.utils.geotools.SimpleFeatureTypes

import scala.collection.SortedSet
import QuerySizeIterator._

/**
 * Iterator that returns number and size of results filtered and results returned.
 */
class QuerySizeIterator extends GeomesaFilteringIterator with HasFeatureDecoder with HasFilter with HasFeatureType {

  var featureBuilder: SimpleFeatureBuilder = null
  var querySizeFeatureEncoder: SimpleFeatureEncoder = null

  override def init(source: SortedKeyValueIterator[Key, Value], options: util.Map[String, String], env: IteratorEnvironment): Unit = {
    super.init(source, options, env)
    featureBuilder = new SimpleFeatureBuilder(querySizeSFT)
    querySizeFeatureEncoder = SimpleFeatureEncoder(querySizeSFT, FeatureEncoding.KRYO)
  }

  // Might need to override seek functionality or findTop
  override def setTopConditionally() = {

//    val featureToInspect = featureDecoder.decode(topValue.get())
//    filter.evaluate(featureToInspect)
    featureBuilder.reset()
    topKey = new Key(source.getTopKey)  // JNH: Feel free to ask me about this later.
    val bytes = new Array[Byte](8)
    ByteBuffer.wrap(bytes).putInt(5)
  
    featureBuilder.set("scanSizeBytes", 5)
    val feature = featureBuilder.buildFeature("feature")

    topValue = new Value(querySizeFeatureEncoder.encode(feature))
  }

}

object QuerySizeIterator {
  val QUERY_SIZE_FEATURE_SFT_STRING =  "geom:Geometry:srid=4326,dtg:Date,dtg_end_time:Date,scanSizeBytes:Long"
  val querySizeSFT = SimpleFeatureTypes.createType("querySize", QUERY_SIZE_FEATURE_SFT_STRING)
}
