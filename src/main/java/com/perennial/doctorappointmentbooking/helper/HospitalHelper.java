package com.perennial.doctorappointmentbooking.helper;
import com.perennial.doctorappointmentbooking.entity.Doctor;
import com.perennial.doctorappointmentbooking.entity.Hospital;
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
public class HospitalHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


    public static boolean checkExcelFormatOfHospital(MultipartFile file)
    {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public static List<Hospital> convertExcelToListOfHospital(InputStream is)
    {
        try
        {
            Workbook workbook= new XSSFWorkbook(is);
            Sheet sheet= workbook.getSheetAt(0);

            Iterator<Row> rows=sheet.iterator();
            List<Hospital>list=new ArrayList<>();
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
               Hospital hospital=new Hospital();
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
                            hospital.setHospitalName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            hospital.setHospitalAddress(currentCell.getStringCellValue());
                            break;
                        case 3:
                            hospital.setPhone((long) currentCell.getNumericCellValue());
                              break;

                        default:

                            break;
                    }
                    cid++;
                }
                list.add(hospital);

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
