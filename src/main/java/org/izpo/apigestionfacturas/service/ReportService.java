package org.izpo.apigestionfacturas.service;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.izpo.apigestionfacturas.model.entity.User;
import org.izpo.apigestionfacturas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private UserRepository userService;

    public ResponseEntity<String> generateReport(String filePathParameter){

        try {
            // Llamar al servicio para generar el reporte
            exportUserReport(filePathParameter);

            return ResponseEntity.ok("Reporte generado correctamente en: " + filePathParameter);
        } catch (JRException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el reporte");
        }
    }


    private void exportUserReport(String filePath) throws JRException, FileNotFoundException {
        // Obtener la lista de usuarios desde el servicio
        List<User> users = userService.findAll();

        // Cargar y compilar el archivo .jrxml
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/employeesReport.jrxml"));


        // Crear un data source a partir de la lista de usuarios
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

        // Parámetros opcionales (pueden estar vacíos)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Nombre del creador");

        // Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(new File(filePath))));

        exporter.exportReport();
    }
}
