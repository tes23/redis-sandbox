package net.cloudenvironment.sandbox;

public class GeoCoordination {
    private String iso2;
    private String countryName;
    private String city;
    private String postCode;
    private Double latitude;
    private Double longitude;

    public GeoCoordination() {
    }

    public GeoCoordination(String iso2, String countryName, String city, String postCode, double latitude, double longitude) {
        this.iso2 = iso2;
        this.countryName = countryName;
        this.city = city;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIso2() {
        return iso2;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getUniqueId() {
        return iso2 + city + postCode;
    }
}
