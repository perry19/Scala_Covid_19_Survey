package com.perrytech

import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.apache.pdfbox.pdmodel.{PDDocument, PDDocumentCatalog, PDDocumentInformation, PDPage, PDPageContentStream}
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject

import java.util.Calendar

// Helper class to plot graph and generate pdf
class MakeReport (val document: PDDocument) {

  val compute = new Compute()
  val frequencyList = compute.listOfFreqValues()
  val mode = compute.calculateMode(frequencyList)
  val mean = compute.mean(frequencyList)
  val median = compute.median(frequencyList)
  val range = compute.range(frequencyList)
  val variance = compute.variance(mean, frequencyList)
  val standardDeviation = compute.standardDeviation(variance)

  def createPDF(): PDDocument ={
    println("PDF created")
    document
  }

  def addPage(): PDDocument={
    val blankPage = new PDPage()
    document.addPage(blankPage)
    document
  }

  def insertMetaData(): Unit = {
    val documentInfo = new PDDocumentInformation()
    documentInfo.setTitle("Covid-19 Survey")
    documentInfo.setCreationDate(Calendar.getInstance())
    documentInfo.setAuthor("Armel Fopa")
    documentInfo.setKeywords("Covid-19, beliefs, survey, statistics")
    documentInfo.setCustomMetadataValue("E-mail", "armelfopa@yahoo.com")
    document.setDocumentInformation(documentInfo)
  }

  def writeOnPage(): Unit={
    val pdImage = PDImageXObject.createFromFile("C:/Users/Fopa Ranita/Desktop/Scala_Projects/src/main/scala/resources/report.PNG", document)
    val page = document.getPage(0)
    val contentStream = new PDPageContentStream(document, page)
    contentStream.drawImage(pdImage, 30, 250)
    contentStream.beginText

    contentStream.setFont(PDType1Font.TIMES_ROMAN, 12)
    contentStream.newLineAtOffset(25, 750)
    contentStream.setLeading(14.5f)
    contentStream.showText("Frequency List: " + compute.listOfFreqValues())
    contentStream.newLine
    contentStream.showText("Mode: " + mode)
    contentStream.newLine
    contentStream.showText("Mean: " + mean)
    contentStream.newLine
    contentStream.showText("Max: " + frequencyList.max)
    contentStream.newLine
    contentStream.showText("Min: " + frequencyList.min)
    contentStream.newLine
    contentStream.showText("Median: " + median)
    contentStream.newLine
    contentStream.showText("Range: " + range)
    contentStream.newLine
    contentStream.showText("Variance: " + variance)
    contentStream.newLine
    contentStream.showText("Standard Deviation: " + standardDeviation)

    contentStream.endText()
    println("Content added")
    contentStream.close()
    document.save("C:/Users/Fopa Ranita/Desktop/my_doc.pdf")
    document.close()
  }
}
