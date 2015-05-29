
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

public class StringBuilderToJson {
	private static final String QUOTATION_MARKS = "\"";// 双引号
	private static String readExcel(int col, String lang) throws Exception {
		String charset = getCharset(lang);
		
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding(charset);
		Workbook workbook = Workbook.getWorkbook(new File("lang.xls"), ws);
		Sheet sheet = workbook.getSheet(0);
		int row;
		String key;
		String value;
		StringBuilder result = new StringBuilder("{");
		Boolean start = false;

		int rows = sheet.getRows();

		for (row = 0; row < rows; row++) {
			key = sheet.getCell(0, row).getContents();
			if (key == "") {
				continue;
			}
			if (key.equals("$end")) {
				break;
			}

			if (start) {
				value = sheet.getCell(col, row).getContents();
				result.append(QUOTATION_MARKS + key + QUOTATION_MARKS + " : "
						+ QUOTATION_MARKS + value + QUOTATION_MARKS + ",");
//				System.out.println(value);
			}

			if (key.equals("$start")) {
				start = true;
			}

		}
		result.deleteCharAt(result.length() -1); //去掉最后一个逗号
		result.append("}");
		String toJson = result.toString();
//		System.out.println(toJson);
		return toJson;

	}
	
	public static String getCharset(String lang){
		if(lang.equals("fr") || lang.equals("es")){
			return "ISO-8859-1";
		}
		return "UTF-8";
	}

	public static void main(String[] args) throws IOException {
		// 读取Excel

		String[] lang = { "cn", "en", "ru", "ja", "ko", "es", "fr" };
		for (int i = 0; i < lang.length; i++) {

			FileOutputStream fos = null;
			try {
				String on = readExcel(i + 1, lang[i]);
				fos = new FileOutputStream(lang[i] + ".json");
				
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				
				osw.write(on);
				osw.flush();//当输出的文件都是大于8kb的话，flush（）可以不写
				fos.close();
				System.out.println(lang[i] + ".json is Done!");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
