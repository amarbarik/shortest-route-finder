package za.co.discovery.assessment.shortestroutefinder.webservice;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import za.co.discovery.assessment.shortestroutefinder.configuration.DataSourceConfig;
import za.co.discovery.assessment.shortestroutefinder.configuration.PersistenceConfig;
import za.co.discovery.assessment.shortestroutefinder.configuration.Resource;
import za.co.discovery.assessment.shortestroutefinder.data.processor.ExcelDataProcessor;
import za.co.discovery.assessment.shortestroutefinder.repository.EdgeRepository;
import za.co.discovery.assessment.shortestroutefinder.repository.TrafficInfoRepository;
import za.co.discovery.assessment.shortestroutefinder.repository.VertexRepository;
import za.co.discovery.assessment.shortestroutefinder.service.ImportDataService;
import za.co.discovery.assessment.shortestroutefinder.service.ShortestPathService;
import za.co.discovery.assessment.shortestroutefinder.webservice.ShortestRouteEndpoint;
import za.co.discovery.assessment.shortestroutefinder.webservice.ShortestRouteRepository;
import za.co.discovery.assessment.shortestroutefinder.webservice.config.WebServiceConfiguration;
import za.co.discovery.assessment.shortestroutefinder.webservice.schema.FindShortestPathToRequest;
import za.co.discovery.assessment.shortestroutefinder.webservice.schema.FindShortestPathToResponse;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PathImpl.class, ShortestPathService.class, ExcelDataProcessor.class,
        Resource.class, DataSourceConfig.class, PersistenceConfig.class, WebServiceConfiguration.class,
        ShortestRouteEndpoint.class, ShortestRouteRepository.class, ImportDataService.class, EdgeRepository.class,
        VertexRepository.class,
        TrafficInfoRepository.class},
        loader = AnnotationConfigContextLoader.class)
public class ShortestPathEndpointTest {

    @Autowired
    private ShortestRouteEndpoint shortestPathEndpoint;

    @Test
    public void verifyThatShortestPathSOAPEndPointIsCorrect() throws Exception {
        // Set Up Fixture
        FindShortestPathToRequest shortestPathRequest = new FindShortestPathToRequest();
        shortestPathRequest.setName("Moon");

        StringBuilder path = new StringBuilder();
        path.append("Earth (A) Moon (B) ");

        FindShortestPathToResponse expectedResponse = new FindShortestPathToResponse();
        expectedResponse.setPath(path.toString());
        expectedResponse.setDistanceTravelled("0.44 (Light Years)");

        //Test
        FindShortestPathToResponse actualResponse = shortestPathEndpoint.getShortestPath(shortestPathRequest);

        // Verify
        assertThat(actualResponse, sameBeanAs(expectedResponse));
        assertThat(actualResponse.getPath(), sameBeanAs(path));
    }

}
