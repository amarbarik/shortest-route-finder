package za.co.discovery.assessment.shortestroutefinder.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import za.co.discovery.assessment.shortestroutefinder.model.Edge;
import za.co.discovery.assessment.shortestroutefinder.repository.EdgeRepository;

import java.util.Locale;

@Component
public class EdgeFormatter implements Formatter<Edge> {

    @Autowired
    EdgeRepository edgeRepository;

    @Override
    public String print(Edge object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Edge parse(String id, Locale locale) throws ParseException {
        return edgeRepository.selectUnique(Long.valueOf(id));
    }
}
