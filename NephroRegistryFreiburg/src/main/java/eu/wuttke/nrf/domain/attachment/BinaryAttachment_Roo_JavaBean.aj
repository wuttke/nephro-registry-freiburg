// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.wuttke.nrf.domain.attachment;

import eu.wuttke.nrf.domain.attachment.AttachmentType;
import eu.wuttke.nrf.domain.attachment.BinaryAttachment;
import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.subject.Subject;

privileged aspect BinaryAttachment_Roo_JavaBean {
    
    public Subject BinaryAttachment.getSubject() {
        return this.subject;
    }
    
    public void BinaryAttachment.setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public AttachmentType BinaryAttachment.getAttachmentType() {
        return this.attachmentType;
    }
    
    public void BinaryAttachment.setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }
    
    public String BinaryAttachment.getLabel() {
        return this.label;
    }
    
    public void BinaryAttachment.setLabel(String label) {
        this.label = label;
    }
    
    public String BinaryAttachment.getDescription() {
        return this.description;
    }
    
    public void BinaryAttachment.setDescription(String description) {
        this.description = description;
    }
    
    public PrecisionDate BinaryAttachment.getAttachmentDate() {
        return this.attachmentDate;
    }
    
    public void BinaryAttachment.setAttachmentDate(PrecisionDate attachmentDate) {
        this.attachmentDate = attachmentDate;
    }
    
}
