package com.perrytech

import Main.sheet

import org.apache.poi.ss.usermodel.Row

import scala.collection.convert.ImplicitConversions._

/** Class to perform necessary computations */

class Compute {

  // Method to calculate the mode
  def calculateMode (values: List[Int]): Int = {
    println("Mode: " + (values.sortWith(_ > _) apply (0)).toString)
    (values.sortWith(_ > _) apply (0))
  }

  def variance(mean: Float, list: List[Int]):Float = {
    val varianceValue = list.map(x => math.pow((x - mean), 2).toFloat).sum / (list.size - 1).toFloat
    println("Variance: " + varianceValue)
    varianceValue
  }

  def standardDeviation(variance: Float):Float={
    val stdDevValue = math.sqrt(variance).toFloat
    println("Standard Deviation: " + stdDevValue)
    stdDevValue
  }

  def range(list: List[Int]):Int={
    val rangeValue = list.max - list.min
    println("Range: " + rangeValue)
    rangeValue
  }

  def median(list: List[Int]) : Float = {
    val medianValue = list.sorted apply(list.size/2)
    println("Median: " + medianValue)
    medianValue
  }

  def mean (list: List[Int]) : Float = {
    val sum = list.sum
    val length = list.length
    val meanValue = sum.toFloat/length.toFloat
    println("Mean: " + meanValue)
    meanValue
  }

  def displayFrequency(values: List[Any] ) : Map[Any, Int] = {
    val frequencyMap : Map[Any, Int] = values.groupBy(l => l).map(t => (t._1, t._2.length))
    println(frequencyMap.drop(1))
    frequencyMap.drop(1)
  }

  //Method to calculate the frequency distribution
  def believeFrequency() : Map[Any, Int]  = {
    val list = Main.sheet.map(row => if (row != sheet.getRow(0)){
      val believes = Option(row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).toString
      believes
    }).toList

    val believeFreq = new Compute().displayFrequency(list)
    println("Map of Frequencies: " + believeFreq)
    believeFreq
  }

  //Method to get the frequency list
  def listOfFreqValues() : List[Int] = {
    val listOfFreq = believeFrequency().values.toList
    println("List of Frequencies: " + listOfFreq)
    listOfFreq
  }
}
