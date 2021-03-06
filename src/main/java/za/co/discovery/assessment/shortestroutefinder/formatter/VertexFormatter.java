package za.co.discovery.assessment.shortestroutefinder.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import za.co.discovery.assessment.shortestroutefinder.model.Vertex;
import za.co.discovery.assessment.shortestroutefinder.repository.VertexRepository;

import java.util.Locale;

@Component
public class VertexFormatter implements Formatter<Vertex> {

    @Autowired
    VertexRepository vertexRepository;

    @Override
    public String print(Vertex object, Locale locale) {
        return object.getId();
    }

    @Override
    public Vertex parse(String id, Locale locale) throws ParseException {
        return vertexRepository.selectUnique(id);
    }
}
