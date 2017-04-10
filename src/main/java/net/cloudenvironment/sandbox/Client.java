package net.cloudenvironment.sandbox;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

import java.util.Collection;
import java.util.Map;

import static net.cloudenvironment.sandbox.GeoCoordinationMapper.toGeoCoordination;
import static net.cloudenvironment.sandbox.GeoCoordinationMapper.toMap;

public class Client {

    private final RedisClient redisClient;
    private final StatefulRedisConnection<String, String> connection;
    private final RedisCommands<String, String> commands;

    public Client(String host, int port, String password) {
        String uri = "redis://" + password + "@" + host + ":" + port + "/0";
        redisClient = RedisClient.create(uri);
        connection = redisClient.connect();
        commands = connection.sync();
    }

    public void add(Collection<GeoCoordination> collection) {
        collection.parallelStream().forEach(this::addCoordination);
    }

    public GeoCoordination get(String key) {
        Map<String, String> fieldMap = commands.hgetall(key);
        return toGeoCoordination(fieldMap);
    }

    private void addCoordination(GeoCoordination coordination) {
        Map<String, String> fieldMap = toMap(coordination);
        commands.hmset(coordination.getUniqueId(), fieldMap);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
        redisClient.shutdown();
    }
}
