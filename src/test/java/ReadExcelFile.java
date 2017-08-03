
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author piyusharora
 */
public class ReadExcelFile {

    String[][] ar;

    public String[][] readExcel() throws IOException, InterruptedException {
        String user_dir = System.getProperty("user.dir");
        String filePath = user_dir + "\\src\\test\\resource";
        String fileName = "book.xlsx";
        File file = new File(filePath + "\\" + fileName);

        FileInputStream inputStream = new FileInputStream(file);
        Workbook Workbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if (fileExtensionName.equals(".xlsx")) {

            Workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {

            Workbook = new HSSFWorkbook(inputStream);
        }

        Sheet Sheet = Workbook.getSheetAt(0);

        int rowCount = ((Sheet.getLastRowNum() - Sheet.getFirstRowNum()) + 1); //number of rows in this case 3
        String ar[][] = new String[rowCount][3];
        for (int i = 0; i < rowCount; i++) {
            Row row = Sheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {
                ar[i][j] = row.getCell(j).getStringCellValue();

            }
        }
        return ar;
    }

}
