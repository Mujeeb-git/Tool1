package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileActions {

	JFileChooser fileChooser;
	String absolutePath, fileNameStr;

	public String chooseFile() throws Exception {
		fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			// get the file
			File file = fileChooser.getSelectedFile();

			absolutePath = file.getAbsolutePath();
			fileNameStr = file.getName().substring(0, file.getName().indexOf("."));

		}
		return absolutePath;
	}

	public String convertFile(String path) throws Exception {

		//String path = absolutePath; // path of the excel file
		//String outputFile = "C:\\Users\\Mujeeb\\Desktop\\" + fileNameStr + ".dat";C:\Users\Mujeeb\Desktop\Book1.xlsx
		String outputFile = "C:\\Users\\Mujeeb\\Desktop\\" + (path.substring(path.lastIndexOf("\\")+1, path.lastIndexOf("."))) + ".dat";
		//System.out.println("File Name: "+outputFile);
		
		InputStream inp = new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(inp);
		
	
		Sheet sheet = null;

		sheet = wb.getSheetAt(0);

		Iterator<Row> rows = sheet.rowIterator();

		final StringBuilder sb = new StringBuilder("");
		while (rows.hasNext()) {
			sb.append("|");
			Row row = (Row) rows.next();

			// how to write to a semicolon delimited dat file here
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();
				sb.append(cell.toString()).append("|");
			}
			sb.append("\n");
			
			//if the above is not working, user slash r and slash n
		}

		File file = new File(outputFile);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		//System.out.println(sb.toString());
		bw.write(sb.toString());
		bw.close();
		return outputFile;
	}
}
