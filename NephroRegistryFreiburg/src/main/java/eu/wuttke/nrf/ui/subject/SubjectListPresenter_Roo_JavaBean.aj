// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.ui.subject;

import eu.wuttke.nrf.domain.subject.SubjectRepository;
import eu.wuttke.nrf.ui.subject.SubjectListPresenter;

privileged aspect SubjectListPresenter_Roo_JavaBean {
    
    public SubjectRepository SubjectListPresenter.getSubjectRepository() {
        return this.subjectRepository;
    }
    
    public void SubjectListPresenter.setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    
}