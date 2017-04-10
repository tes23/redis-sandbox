package net.cloudenvironment.sandbox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RedisClientTest {
    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client("localhost", 6379, "redis");
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void testMap(){
        // given
        final Collection<GeoCoordination> coordinations = createCoordinations();

        // when
        client.add(coordinations);

        // then
        GeoCoordination coordination = client.get("FRParis75001");
        assertThat(coordination.getCity(), is("Paris"));
        assertThat(coordination.getLatitude(), is(48.8534100));
        assertThat(coordination.getLongitude(), is(2.3488000));


    }

    private static Collection<GeoCoordination> createCoordinations() {
        List<GeoCoordination> coordinations = new ArrayList<GeoCoordination>();

        GeoCoordination coordination =
                new GeoCoordination("DE", "Germany", "Hamburg", "20095", 53.551086, 9.993682);
        coordinations.add(coordination);
        coordination =
                new GeoCoordination("US", "United States of America", "Los Angeles", "34.064/-118.441", 34.052235, -118.243683);
        coordinations.add(coordination);
        coordination =
                new GeoCoordination("FR", "France", "Paris", "75001", 48.8534100, 2.3488000);
        coordinations.add(coordination);

        return coordinations;
    }

}
