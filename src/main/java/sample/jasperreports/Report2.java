package sample.jasperreports;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

public class Report2 {

	public static void main(String[] args) throws Exception {
		Map<String, Object> parameters = new HashMap<>();

		JRCsvDataSource dataSource = new JRCsvDataSource(Report2.class.getResourceAsStream("report2.csv"),
				"Shift_JIS");
		dataSource.setUseFirstRowAsHeader(true);

		try (InputStream template = Report2.class.getResourceAsStream("report2.jrxml")) {
			JasperReport report = JasperCompileManager.compileReport(template);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/java/sample/jasperreports/report2.pdf");
		}
	}
}
