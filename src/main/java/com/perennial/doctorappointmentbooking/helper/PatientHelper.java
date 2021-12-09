package com.perennial.doctorappointmentbooking.helper;

import com.perennial.doctorappointmentbooking.entity.Hospital;
import com.perennial.doctorappointmentbooking.entity.Patient;
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

public class PatientHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    public static boolean checkExcelFormatOfPatient(MultipartFile file)
    {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public static List<Patient> convertExcelToListOfPatient(InputStream is)
    {
        try
        {
            Workbook workbook= new XSSFWorkbook(is);
            Sheet sheet= workbook.getSheetAt(0);

            Iterator<Row> rows=sheet.iterator();
            List<Patient>list=new ArrayList<>();
            int rowNumber=0;

            while (rows.hasNext())
            {
                Row currentRow= rows.next();
                if(rowNumber==0)
                {

                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow=currentRow.iterator();
                Patient p=new Patient();
                int cid=0;

                while (cellsInRow.hasNext())
                {
                    Cell currentCell=cellsInRow.next();
                    switch (cid)
                    {

                        case 0:
//
                            break;
                        case 1:
                            p.setFirst_name(currentCell.getStringCellValue());
                            break;
                        case 2:
                            p.setLast_name(currentCell.getStringCellValue());
                            break;
                        case 3:
                            p.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            p.setAddress(currentCell.getStringCellValue());
                            break;
                        case 5:
                            p.setGender(currentCell.getStringCellValue());
                            break;
                        case  6:
                            p.setPhone((long) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            p.setAge((int) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            p.setReason(currentCell.getStringCellValue());
                            break;
                        default:

                            break;
                    }
                    cid++;
                }
                list.add(p);

                list.forEach(s-> System.out.println(s.toString()));

            }
            workbook.close();
            return list;
        }
        catch ( IOException e)
        {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());

        }

    }

}
