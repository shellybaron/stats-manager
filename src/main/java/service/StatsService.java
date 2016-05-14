package service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sbaron
 * @since 5/14/16
 */
public class StatsService {

    private Map<String, Long> eventTypes = new HashMap<String, Long>();
    private Map<String, Long> words = new HashMap<String, Long>();

    public void setEventTypes(Map<String, Long> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public void setWords(Map<String, Long> words) {
        this.words = words;
    }

    public Map<String, Long> getEventTypesMap() {
        return eventTypes;
    }

    public Map<String, Long> getWordsMap() {
        return words;
    }

    public String getCountByType() {
        StringBuilder sb = new StringBuilder();

        for(String eventType : eventTypes.keySet()) {
            sb.append(eventType);
            sb.append(": ");
            sb.append(eventTypes.getOrDefault(eventType, 0L));
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getCountByWord() {
        StringBuilder sb = new StringBuilder();

        for(String word : words.keySet()) {
            sb.append(word);
            sb.append(": ");
            sb.append(words.getOrDefault(word, 0L));
            sb.append("\n");
        }

        return sb.toString();
    }
}
