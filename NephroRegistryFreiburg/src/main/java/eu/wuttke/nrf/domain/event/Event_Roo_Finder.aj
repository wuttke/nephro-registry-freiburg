// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.event;

import eu.wuttke.nrf.domain.event.Event;
import eu.wuttke.nrf.domain.subject.Subject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Event_Roo_Finder {
    
    public static TypedQuery<Event> Event.findEventsBySubject(Subject subject) {
        if (subject == null) throw new IllegalArgumentException("The subject argument is required");
        EntityManager em = Event.entityManager();
        TypedQuery<Event> q = em.createQuery("SELECT o FROM Event AS o WHERE o.subject = :subject", Event.class);
        q.setParameter("subject", subject);
        return q;
    }
    
}