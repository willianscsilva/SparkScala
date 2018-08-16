package main.scala.run

import main.scala.spark.SparkContextSingleton
import com.datastax.spark.connector._
import com.datastax.spark.connector.cql._
import java.util.Calendar

object Main {
  def main(args: Array[String]) {
    val sc = SparkContextSingleton.getSparkContext()

    val cart = sc.cassandraTable("keyspace", "table")
    .where("created_at >= ? AND created_at <= ?", "2018-08-07", "2018-08-07")
    
    cart.foreach(action => {
      println(action.getString("fieldTable"))
    })
    
//    val data = Array(1, 2, 3, 4, 5)
//    val distData = sc.parallelize(data)
//    distData.foreach(data => {
//      println(data)
//    })
    sc.stop()
  }
}