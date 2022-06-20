package com.cg.user.excel;

import java.io.ByteArrayInputStream;
import lombok.Generated;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cg.user.model.UserModel;
@Generated
public class ExcelHelper {

	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	 static String[] HEADER = { "EmpID", "Name", "Email", "Contact No" ,"Grade" , "Joining Date" , "Batch" , "Status" , "Trained By" , "Mentor Name" , "Mentor Email", "Reporting Manager Name", "Reporting Manager Email", "Certification" , "Enrolled Status", "Enrolled Date" , "Deadline", "Attempts" , "1st" , "2nd" , "3rd" , "Certification Status" , "Voucher Status" };
	 static String SHEET = "Employee Details";
	 
	 public static ByteArrayInputStream usersToExcel(List<UserModel> users) {
		 
	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	    	
	      Sheet sheet = workbook.createSheet(SHEET);
	      // Header
	      Row headerRow = sheet.createRow(0);
	      CellStyle styleHeader = workbook.createCellStyle();
	      Font headerFont = workbook.createFont();
	      headerFont.setBold(true);
	      styleHeader.setFont(headerFont);
	      styleHeader.setFont(null);
	      styleHeader.setWrapText(true);
	      styleHeader.setAlignment(HorizontalAlignment.CENTER);
	      
	      for (int col = 0; col < HEADER.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADER[col]);
	        cell.setCellStyle(styleHeader);
	        sheet.setColumnWidth(col, 25*256);
	      }
	      
	      int rowIdx = 1;
	      CellStyle styleRow = workbook.createCellStyle();
	      styleRow.setWrapText(true);
	      styleRow.setAlignment(HorizontalAlignment.CENTER);
	      for (UserModel user : users) {
	    	
	        Row row = sheet.createRow(rowIdx++);
	        
	        Cell c0 = row.createCell(0);
	        c0.setCellValue(user.getEmpID());
	        c0.setCellStyle(styleRow);
	        
	        Cell c1 = row.createCell(1);
	        c1.setCellValue(user.getEmpName());
	        c1.setCellStyle(styleRow);
	        
	        Cell c2 = row.createCell(2);
	        c2.setCellValue(user.getEmail());
	        c2.setCellStyle(styleRow);
	        
	        Cell c3 = row.createCell(3);
	        c3.setCellValue(user.getContactNumber());
	        c3.setCellStyle(styleRow);

	        Cell c4 = row.createCell(4);
	        c4.setCellValue(user.getGrade());
	        c4.setCellStyle(styleRow);
	        
	        Cell c5 = row.createCell(5);
	        c5.setCellValue(user.getJoiningDate());
	        c5.setCellStyle(styleRow);

	        Cell c6 = row.createCell(6);
	        c6.setCellValue(user.getBatchName());
	        c6.setCellStyle(styleRow);

	        Cell c7 = row.createCell(7);
	        c7.setCellValue(user.getStatus());
	        c7.setCellStyle(styleRow);

	        Cell c8 = row.createCell(8);
	        c8.setCellValue(user.getTrainedBy());
	        c8.setCellStyle(styleRow);

	        Cell c9 = row.createCell(9);
	        c9.setCellValue(user.getMentorName());
	        c9.setCellStyle(styleRow);

	        Cell c10 = row.createCell(10);
	        c10.setCellValue(user.getMentorEmail());
	        c10.setCellStyle(styleRow);

	        Cell c11 = row.createCell(11);
	        c11.setCellValue(user.getReportingManagerName());
	        c11.setCellStyle(styleRow);

	        Cell c12 = row.createCell(12);
	        c12.setCellValue(user.getReportingManagerEmail());
	        c12.setCellStyle(styleRow);

	        Cell c13 = row.createCell(13);
	        c13.setCellValue(user.getCertificationName());
	        c13.setCellStyle(styleRow);

	        Cell c14 = row.createCell(14);
	        c14.setCellValue(user.getEnrolledStatus());
	        c14.setCellStyle(styleRow);
	        
	        Cell c15 = row.createCell(15);
	        c15.setCellValue(user.getEnrolledDate());
	        c15.setCellStyle(styleRow);

	        Cell c16 = row.createCell(16);
	        c16.setCellValue(user.getDeadlineDate());
	        c16.setCellStyle(styleRow);

	        Cell c17 = row.createCell(17);
	        c17.setCellValue(user.getNumberOfAttempts());
	        c17.setCellStyle(styleRow);

	        Cell c18 = row.createCell(18);
	        c18.setCellValue(user.getFirstAttempt());
	        c18.setCellStyle(styleRow);
	        
	        Cell c19 = row.createCell(19);
	        c19.setCellValue(user.getSecondAttempt());
	        c19.setCellStyle(styleRow);

	        Cell c20 = row.createCell(20);
	        c20.setCellValue(user.getThirdAttempt());
	        c20.setCellStyle(styleRow);

	        Cell c21 = row.createCell(21);
	        c21.setCellValue(user.getCertificationStatus());
	        c21.setCellStyle(styleRow);

	        Cell c22 = row.createCell(22);
	        c22.setCellValue(user.getVoucherStatus());
	        c22.setCellStyle(styleRow);
	        
	      }
	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	      
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }
	
}
