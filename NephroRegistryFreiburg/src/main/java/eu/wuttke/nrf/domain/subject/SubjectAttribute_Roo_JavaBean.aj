// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.subject;

import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.domain.subject.SubjectAttribute;

privileged aspect SubjectAttribute_Roo_JavaBean {
    
    public Subject SubjectAttribute.getSubject() {
        return this.subject;
    }
    
    public void SubjectAttribute.setSubject(Subject subject) {
        this.subject = subject;
    }
    
}
