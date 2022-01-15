package com.perrytech

import org.apache.pdfbox.pdmodel.PDDocument

object Main extends App {

  /**
    Theme: Beliefs and feelings towards the vaccine

    Question: Do you think the vaccine is a good idea?

    Answers: Given in a scale of 1 to 5

    Outcomes:
    Come out with some stats showing the number of people who agree and don't agree,
    plot a graph: Initially a bar chart showing those who agree and don't can be sufficient...
    Write the results in a pdf file

    TODO

   Add title
   and other details info: date, name, etc...
   context
   documentation

   Build a web app that get excel files from users and return them some stats they can download
   case study: Current project...
   */

    val sheet = new ReadExcelSheet().sheet
    val document = new PDDocument
    val makeReport = new MakeReport(document)
    val createPDF = makeReport.createPDF()
    val page = makeReport.addPage()
    val metaData: Unit = makeReport.insertMetaData()
    val writeOnPage: Unit = makeReport.writeOnPage()
    val compute = new Compute()
    val frequencyList = compute.listOfFreqValues()
    val mode = compute.calculateMode(frequencyList)
    val mean = compute.mean(frequencyList)
    val median = compute.median(frequencyList)
    val range = compute.range(frequencyList)
    val variance = compute.variance(mean, frequencyList)
    val standardDeviation = compute.standardDeviation(variance)
  }
