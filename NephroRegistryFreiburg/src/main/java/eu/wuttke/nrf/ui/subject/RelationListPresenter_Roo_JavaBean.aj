// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.ui.subject;

import eu.wuttke.nrf.domain.subject.RelationRepository;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.export.PedFileExporter;
import eu.wuttke.nrf.ui.subject.RelationListPresenter;

privileged aspect RelationListPresenter_Roo_JavaBean {
    
    public Subject RelationListPresenter.getSubject() {
        return this.subject;
    }
    
    public void RelationListPresenter.setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public RelationRepository RelationListPresenter.getRelationRepository() {
        return this.relationRepository;
    }
    
    public void RelationListPresenter.setRelationRepository(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }
    
    public PedFileExporter RelationListPresenter.getPedFileExporter() {
        return this.pedFileExporter;
    }
    
    public void RelationListPresenter.setPedFileExporter(PedFileExporter pedFileExporter) {
        this.pedFileExporter = pedFileExporter;
    }
    
}