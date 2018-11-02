package za.co.discovery.assessment.shortestroutefinder.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class Resource {

    private static final String EXCEL_FILENAME = "/interstellar.xlsx";

    //or
    @Value("${import.data.file.location}")
    private String excelFilename;

    private Logger logger = Logger.getLogger(Resource.class.getName());

    @Bean
    public File getFileResource() {
        URL resource = getClass().getResource(EXCEL_FILENAME);
        File file = null;
        try {
            file = new File(resource.toURI());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to read the excel file " + EXCEL_FILENAME + ", Exit Interstellar Transport System!");
            System.exit(1);
        }
        return file;
    }
}
