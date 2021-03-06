package za.co.discovery.assessment.shortestroutefinder.webservice;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import za.co.discovery.assessment.shortestroutefinder.configuration.DataSourceConfig;
import za.co.discovery.assessment.shortestroutefinder.configuration.PersistenceConfig;
import za.co.discovery.assessment.shortestroutefinder.dto.ShortestRouteModel;
import za.co.discovery.assessment.shortestroutefinder.helper.Graph;
import za.co.discovery.assessment.shortestroutefinder.model.Edge;
import za.co.discovery.assessment.shortestroutefinder.model.TrafficInfo;
import za.co.discovery.assessment.shortestroutefinder.model.Vertex;
import za.co.discovery.assessment.shortestroutefinder.service.ImportDataService;
import za.co.discovery.assessment.shortestroutefinder.service.ShortestPathService;
import za.co.discovery.assessment.shortestroutefinder.webservice.ShortestRouteRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PathImpl.class, ShortestPathService.class,
        DataSourceConfig.class, PersistenceConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class ShortestPathRepositoryTest {
    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager platformTransactionManager;

    @Autowired
    private ShortestPathService shortestPathService;

    @Test
    public void verifyThatDataInitializeAndGiveCorrectPath() throws Exception {

        // SetUp Fixture
        ImportDataService importDataService = mock(ImportDataService.class);

        Vertex vertexA = new Vertex("A", "Earth");
        Vertex vertexF = new Vertex("F", "Pluto");
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(vertexA);
        vertices.add(vertexF);
        Edge edge1 = new Edge("30", vertexA, vertexF, 0.17f);
        List<Edge> edges = new ArrayList<>();
        edges.add(edge1);
        TrafficInfo traffic = new TrafficInfo("1", edge1, 4f);
        List<TrafficInfo> traffics = new ArrayList<>();
        traffics.add(traffic);

        StringBuilder path = new StringBuilder();
        Vertex expectedSource = vertices.get(0);
        Vertex expectedDestination = vertices.get(1);
        Graph graph = new Graph(vertices, edges, traffics);
        LinkedList<Vertex> pathList = new LinkedList<>();
        pathList.add(expectedSource);
        pathList.add(expectedDestination);
        when(importDataService.selectGraph()).thenReturn(graph);
        when(importDataService.getVertexByName(expectedDestination.getName())).thenReturn(expectedDestination);
        when(importDataService.getVertexById(expectedDestination.getId())).thenReturn(expectedDestination);
        when(importDataService.findEdgeBetweenVertexes(vertexA, vertexF)).thenReturn(edge1);

        path.append("Earth (A) Pluto (F) ");
        ShortestRouteRepository pathRepository = new ShortestRouteRepository(platformTransactionManager, importDataService, shortestPathService);

        // Test
        pathRepository.initData();
        ShortestRouteModel shortestPath = pathRepository.getShortestPath("Pluto");
        String actualPath = shortestPath.getThePath();

        //Verify
        assertThat(actualPath, sameBeanAs(path.toString()));
    }
}
