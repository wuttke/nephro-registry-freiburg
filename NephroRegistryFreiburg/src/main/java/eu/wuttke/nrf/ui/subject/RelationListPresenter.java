package eu.wuttke.nrf.ui.subject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import eu.wuttke.nrf.domain.subject.Relation;
import eu.wuttke.nrf.domain.subject.RelationRepository;
import eu.wuttke.nrf.domain.subject.Subject;
import eu.wuttke.nrf.export.PedFileExporter;
import eu.wuttke.nrf.ui.presenter.EditorPresenter;
import eu.wuttke.nrf.ui.presenter.ListPresenter;

@RooJavaBean
@Configurable
public class RelationListPresenter 
extends ListPresenter<Relation, RelationListView> {
	
	private Subject subject;
	
	public RelationListPresenter() {
		super(new RelationListView());
		getListView().addDownloadPedAction(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				startPedDownload();
			}
		});
	}
	
	protected void startPedDownload() {
		Resource resource = new StreamResource(new StreamSource() {
			private static final long serialVersionUID = 1L;
			@Override
			public InputStream getStream() {
				try {
					String ped = pedFileExporter.buildPedFileForFamily(subject);
					return new ByteArrayInputStream(ped.getBytes("UTF8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}, "family.ped");

		FileDownloader fd = new FileDownloader(resource);
		fd.extend(getListView().getDownloadPedButton());
	}

	public void setParentSubject(Subject parentSubject) {
		this.subject = parentSubject;
	}
	
	@Override
	public Collection<Relation> loadEntities() {
		return relationRepository.findFirstDegreeFamily(subject);
	}
	
	@Override
	public EditorPresenter<Relation, ?> createEditorPresenter() {
		return new RelationEditorPresenter(subject, this);
	}

	@Autowired
	private RelationRepository relationRepository;
	
	@Autowired
	private PedFileExporter pedFileExporter;
	
}
