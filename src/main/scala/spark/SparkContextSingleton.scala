package main.scala.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SparkContextSingleton {

  private var CONTEXT: SparkContext = null

  def getSparkConf(): SparkConf = {
    return new SparkConf().setAppName("Spark Scala").setMaster("local[*]")
                 .set("spark.cassandra.connection.host", "")//host
      					 .set("spark.cassandra.auth.username", "")//login
      					 .set("spark.cassandra.auth.password", "")//password
  }

  def getSparkContext(): SparkContext = {
    if (CONTEXT == null) {
      CONTEXT = new SparkContext(getSparkConf())
    }
    return CONTEXT
  }
}