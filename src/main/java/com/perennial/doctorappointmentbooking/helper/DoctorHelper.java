package com.perennial.doctorappointmentbooking.helper;
import com.perennial.doctorappointmentbooking.entity.Doctor;
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

public class DoctorHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean checkExcelFormatOfDoctor(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public static List<Doctor> convertExcelToListOfDoctor(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            List<Doctor> list = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                if (rowNumber == 0) {

                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Doctor doctor = new Doctor();
                int cid = 0;

                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cid) {

                        case 0:

                            break;
                        case 1:
                            doctor.setLicenceNumber(currentCell.getStringCellValue());
                            break;
                        case 2:
                            doctor.setFirstName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            doctor.setLastName(currentCell.getStringCellValue());
                            break;
                        case 4:
                            doctor.setEmail(currentCell.getStringCellValue());
                            break;
                        case 5:
                            doctor.setAddress(currentCell.getStringCellValue());
                            break;
                        case 6:
                            doctor.setPhone((long) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            doctor.setSpeciality(currentCell.getStringCellValue());
                            break;

                        case 8:
                            doctor.setExperience((int) currentCell.getNumericCellValue());
                            break;
                        case 9:
                            doctor.setEducation(currentCell.getStringCellValue());
                            break;

                        case 10:
                            doctor.setStatus(currentCell.getStringCellValue());
                            break;
                        default:

                            break;
                    }
                    cid++;
                }
                list.add(doctor);

            }
            workbook.close();
            return list;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());

        }

    }

}
