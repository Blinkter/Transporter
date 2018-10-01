/*package transporter.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.engine.config.spi.ConfigurationService.Converter;

public final class LocalDateConverter implements Converter<String, Date> {

    private final SimpleDateFormat formatter;

    public LocalDateConverter(String dateFormat) {
        this.formatter = new SimpleDateFormat(dateFormat);
    }

    public Date convert(String source) {
        try {
            return this.formatter.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}*/