package openclose;

import java.time.LocalDateTime;
import java.util.*;

public class InternetSessionHistory {

    public static class InternetSession {

        private LocalDateTime begin;
        private Long subscriberId;
        private long dataUsed;

        public InternetSession(Long subscriberId, LocalDateTime begin, long dataUsed) {
            this.begin = begin;
            this.subscriberId = subscriberId;
            this.dataUsed = dataUsed;
        }

        public LocalDateTime getBegin() {
            return begin;
        }

        public Long getSubscriberId() {
            return subscriberId;
        }

        public long getDataUsed() {
            return dataUsed;
        }
    }

    private static final Map<Long, List<InternetSession>> SESSIONS = new HashMap<>();

    public synchronized static List<InternetSession> getCurrentSessions(Long subscriberId) {
        if (!SESSIONS.containsKey(subscriberId)) {
            return Collections.emptyList();
        }
        return SESSIONS.get(subscriberId);
    }

    public synchronized static void addSession(Long subscriberId, LocalDateTime begin, long dataUsed) {
        List<InternetSession> sessions;
        if (!SESSIONS.containsKey(subscriberId)) {
            sessions = new LinkedList<>();
            SESSIONS.put(subscriberId, sessions);
        } else {
            sessions = SESSIONS.get(subscriberId);
        }
        sessions.add(new InternetSession(subscriberId, begin, dataUsed));
    }
}
