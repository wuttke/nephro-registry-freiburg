// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.sample;

import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.sample.Sample;
import eu.wuttke.nrf.domain.sample.SampleType;
import eu.wuttke.nrf.domain.subject.Subject;

privileged aspect Sample_Roo_JavaBean {
    
    public String Sample.getSampleId() {
        return this.sampleId;
    }
    
    public void Sample.setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }
    
    public Subject Sample.getSubject() {
        return this.subject;
    }
    
    public void Sample.setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public SampleType Sample.getSampleType() {
        return this.sampleType;
    }
    
    public void Sample.setSampleType(SampleType sampleType) {
        this.sampleType = sampleType;
    }
    
    public PrecisionDate Sample.getSampleDate() {
        return this.sampleDate;
    }
    
    public void Sample.setSampleDate(PrecisionDate sampleDate) {
        this.sampleDate = sampleDate;
    }
    
    public Double Sample.getAmount() {
        return this.amount;
    }
    
    public void Sample.setAmount(Double amount) {
        this.amount = amount;
    }
    
    public Double Sample.getOpticalDensity() {
        return this.opticalDensity;
    }
    
    public void Sample.setOpticalDensity(Double opticalDensity) {
        this.opticalDensity = opticalDensity;
    }
    
    public Double Sample.getConcentration() {
        return this.concentration;
    }
    
    public void Sample.setConcentration(Double concentration) {
        this.concentration = concentration;
    }
    
    public String Sample.getComment() {
        return this.comment;
    }
    
    public void Sample.setComment(String comment) {
        this.comment = comment;
    }
    
}
