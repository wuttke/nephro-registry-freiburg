// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.result;

import eu.wuttke.nrf.domain.result.Result;
import eu.wuttke.nrf.domain.result.ResultItem;
import eu.wuttke.nrf.domain.result.ResultItemType;
import eu.wuttke.nrf.domain.subject.Subject;
import java.util.Date;

privileged aspect ResultItem_Roo_JavaBean {
    
    public Subject ResultItem.getSubject() {
        return this.subject;
    }
    
    public void ResultItem.setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public Result ResultItem.getResult() {
        return this.result;
    }
    
    public void ResultItem.setResult(Result result) {
        this.result = result;
    }
    
    public Date ResultItem.getItemDateTime() {
        return this.itemDateTime;
    }
    
    public void ResultItem.setItemDateTime(Date itemDateTime) {
        this.itemDateTime = itemDateTime;
    }
    
    public ResultItemType ResultItem.getItemType() {
        return this.itemType;
    }
    
    public void ResultItem.setItemType(ResultItemType itemType) {
        this.itemType = itemType;
    }
    
    public String ResultItem.getRawType() {
        return this.rawType;
    }
    
    public void ResultItem.setRawType(String rawType) {
        this.rawType = rawType;
    }
    
    public String ResultItem.getLabel() {
        return this.label;
    }
    
    public void ResultItem.setLabel(String label) {
        this.label = label;
    }
    
    public String ResultItem.getStringValue() {
        return this.stringValue;
    }
    
    public void ResultItem.setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    
    public Double ResultItem.getNumericValue() {
        return this.numericValue;
    }
    
    public void ResultItem.setNumericValue(Double numericValue) {
        this.numericValue = numericValue;
    }
    
}
