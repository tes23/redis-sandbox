package net.cloudenvironment.sandbox;

import java.util.HashMap;
import java.util.Map;

public class GeoCoordinationMapper {
    private enum FIELDS {
        iso2, countryName, city, postCode, latitude, longitude
    }

    public static GeoCoordination toGeoCoordination(Map<String, String> fieldMap) {
        GeoCoordination coordination = new GeoCoordination();
        coordination.setIso2(getValue(fieldMap, FIELDS.iso2));
        coordination.setCountryName(getValue(fieldMap, FIELDS.countryName));
        coordination.setCity(getValue(fieldMap, FIELDS.city));
        coordination.setPostCode(getValue(fieldMap, FIELDS.postCode));
        coordination.setLatitude(getDoubleValue(fieldMap, FIELDS.latitude));
        coordination.setLongitude(getDoubleValue(fieldMap, FIELDS.longitude));

        return coordination;
    }

    public static Map<String, String> toMap(GeoCoordination coordination) {
        Map<String, String> fieldMap = new HashMap<>(6);
        fieldMap.put("iso2", coordination.getIso2());
        fieldMap.put("city", coordination.getCity());
        fieldMap.put("countryName", coordination.getCountryName());
        fieldMap.put("postCode", coordination.getPostCode());
        fieldMap.put("latitude", "" + coordination.getLatitude());
        fieldMap.put("longitude", "" + coordination.getLongitude());

        return fieldMap;
    }

    private static String getValue(Map<String, String> fieldMap, FIELDS key) {
        return fieldMap.get(key.name());
    }

    private static Double getDoubleValue(Map<String, String> fieldMap, FIELDS key) {
        Double numberValue;

        String value = getValue(fieldMap, key);
        try {
            numberValue = Double.valueOf(value);
        } catch (NumberFormatException e) {
            numberValue = null;
        }

        return numberValue;
    }
}
