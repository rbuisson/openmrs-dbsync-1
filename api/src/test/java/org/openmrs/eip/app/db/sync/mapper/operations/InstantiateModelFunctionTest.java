package org.openmrs.eip.app.db.sync.mapper.operations;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openmrs.eip.app.db.sync.exception.SyncException;
import org.openmrs.eip.app.db.sync.model.BaseModel;
import org.openmrs.eip.app.db.sync.service.MapperService;
import org.openmrs.eip.app.db.sync.entity.MockedEntity;
import org.openmrs.eip.app.db.sync.MockedModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InstantiateModelFunctionTest {

    @Mock
    private MapperService<MockedEntity, MockedModel> mapperService;

    private InstantiateModelFunction<MockedEntity, MockedModel> instantiateModel;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        instantiateModel = new InstantiateModelFunction<>(mapperService);
    }

    @Test
    public void apply_should_instantiate_model() {
        // Given
        MockedEntity entity = new MockedEntity(1L, "uuid");
        Mockito.<Class<? extends BaseModel>>when(mapperService.getCorrespondingModelClass(entity)).thenReturn(MockedModel.class);

        // When
        Context<MockedEntity, MockedModel> result = instantiateModel.apply(entity);

        // Then
        assertNotNull(result);
        assertNotNull(result.getModel());
        assertEquals(entity, result.getEntity());
    }

    @Test(expected = SyncException.class)
    public void apply_should_throw_exception() {
        // Given
        MockedEntity entity = new MockedEntity(1L, "uuid");
        Mockito.<Class<? extends BaseModel>>when(mapperService.getCorrespondingModelClass(entity)).thenReturn(MockedModelWithNoConstructor.class);

        // When
        instantiateModel.apply(entity);

        // Then
    }

    private class MockedModelWithNoConstructor extends MockedModel {}
}
