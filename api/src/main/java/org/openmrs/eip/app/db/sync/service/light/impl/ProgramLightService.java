package org.openmrs.eip.app.db.sync.service.light.impl;

import org.openmrs.eip.app.db.sync.entity.light.ConceptLight;
import org.openmrs.eip.app.db.sync.entity.light.ProgramLight;
import org.openmrs.eip.app.db.sync.repository.OpenmrsRepository;
import org.openmrs.eip.app.db.sync.service.light.AbstractLightService;
import org.openmrs.eip.app.db.sync.service.light.LightService;
import org.springframework.stereotype.Service;

@Service
public class ProgramLightService extends AbstractLightService<ProgramLight> {
	
	private LightService<ConceptLight> conceptService;
	
	public ProgramLightService(final OpenmrsRepository<ProgramLight> repository,
	    final LightService<ConceptLight> conceptService) {
		super(repository);
		this.conceptService = conceptService;
	}
	
	@Override
	protected ProgramLight createPlaceholderEntity(final String uuid) {
		ProgramLight program = new ProgramLight();
		program.setDateCreated(DEFAULT_DATE);
		program.setCreator(DEFAULT_USER_ID);
		program.setName(DEFAULT_STRING + " - " + uuid);
		program.setConcept(conceptService.getOrInitPlaceholderEntity());
		
		return program;
	}
}
