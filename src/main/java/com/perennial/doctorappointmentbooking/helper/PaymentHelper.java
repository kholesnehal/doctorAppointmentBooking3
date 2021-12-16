package com.perennial.doctorappointmentbooking.helper;

import com.perennial.doctorappointmentbooking.entity.Payment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean checkExcelFormatOfPayment(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public static List<Payment> convertExcelToListOfPPayment(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            List<Payment> list = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {

                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Payment payment = new Payment();
                int cid = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cid) {

                        case 0:

                            break;
                        case 1:
                            payment.setPaymentMode(currentCell.getStringCellValue());
                            break;
                        case 2:
                            payment.setAmmount(currentCell.getNumericCellValue());
                            break;
                        case 3:
                            payment.setPaymentDate(currentCell.getDateCellValue());
                            break;
                        case 4:
                            payment.setPaymentTime(currentCell.getStringCellValue());
                            break;
                        default:

                            break;
                    }
                    cid++;
                }
                list.add(payment);
            }
            workbook.close();
            return list;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());

        }

    }

}
