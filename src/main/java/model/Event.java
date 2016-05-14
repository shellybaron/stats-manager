package model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author sbaron
 * @since 5/14/16
 */
public class Event {

    @JsonProperty("event_type")
    private String type;

    @JsonProperty("data")
    private String data;

    @JsonProperty("timestamp")
    private long ts;

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public long getTs() {
        return ts;
    }
}
