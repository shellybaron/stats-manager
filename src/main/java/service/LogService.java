package service;

import model.Event;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


/**
 * @author sbaron
 * @since 5/14/16
 */
public class LogService {

    @Autowired
    StatsService statsService;

    public void handleLogs() throws IOException {

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Process p = Runtime.getRuntime().exec("/tmp/generator-macosx-amd64");
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(p.getInputStream()));
                    String line = null;

                    while ((line = in.readLine()) != null) {
                       Event event = eventObjGenerator(line);
                        if(event != null) {
                            addType(event.getType());
                            addWord(event.getData());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void addType(String type) {
        Map<String, Long> eventTypes = statsService.getEventTypesMap();
        eventTypes.put(type, eventTypes.getOrDefault(type, 0L) + 1);
        statsService.setEventTypes(eventTypes);
    }

    private void addWord(String word) {
        Map<String, Long> words = statsService.getWordsMap();
        words.put(word, words.getOrDefault(word, 0L) + 1);
        statsService.setWords(words);
    }


    private Event eventObjGenerator(String jsonEvent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Event event = mapper.readValue(jsonEvent, Event.class);
            return event;
        } catch (IOException e) {
            return null;
        }
    }
}
