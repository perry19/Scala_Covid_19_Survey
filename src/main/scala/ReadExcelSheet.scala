package com.perrytech

import org.apache.poi.ss.usermodel.{Sheet, Workbook, WorkbookFactory}

import java.io.File

class ReadExcelSheet {

  val fileUrl = new File(  "C:/Users/Fopa Ranita/Desktop/Scala_Projects/src/main/scala/resources/survey_sheet.xlsx")

  // Read the content of the workbook
  val workbook: Workbook = WorkbookFactory.create(fileUrl)

  //Get the first/desired sheet from the workbook
  val sheet: Sheet = workbook.getSheetAt(0)

  def returnSheet(): Sheet = sheet
}
