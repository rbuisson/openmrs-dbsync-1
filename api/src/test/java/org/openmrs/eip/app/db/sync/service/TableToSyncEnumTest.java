package org.openmrs.eip.app.db.sync.service;

import org.junit.Test;
import org.openmrs.eip.app.db.sync.exception.SyncException;
import org.openmrs.eip.app.db.sync.model.PersonModel;
import org.openmrs.eip.app.db.sync.MockedModel;
import org.openmrs.eip.app.db.sync.entity.MockedEntity;
import org.openmrs.eip.app.db.sync.entity.Person;

import static org.junit.Assert.assertEquals;

public class TableToSyncEnumTest {

    @Test
    public void getTableToSyncEnum_should_return_enum() {
        // Given
        String nameString = "person";

        // When
        TableToSyncEnum result = TableToSyncEnum.getTableToSyncEnum(nameString);

        // Then
        assertEquals(TableToSyncEnum.PERSON, result);
    }

    @Test
    public void getTableToSyncEnum_with_model_class_should_return_enum() {
        // Given
        Class<PersonModel> personModelClass = PersonModel.class;

        // When
        TableToSyncEnum result = TableToSyncEnum.getTableToSyncEnum(personModelClass);

        // Then
        assertEquals(TableToSyncEnum.PERSON, result);
    }

    @Test(expected = SyncException.class)
    public void getTableToSyncEnum_with_model_class_should_throw_exception() {
        // Given
        Class<MockedModel> personModelClass = MockedModel.class;

        // When
        TableToSyncEnum result = TableToSyncEnum.getTableToSyncEnum(personModelClass);

        // Then
    }

    @Test
    public void getModelClass_should_return_model_class() {
        // Given
        Person person = new Person();

        // When
        Class result = TableToSyncEnum.getModelClass(person);

        // Then
        assertEquals(PersonModel.class, result);
    }

    @Test(expected = SyncException.class)
    public void getModelClass_should_throw_exception() {
        // Given
        MockedEntity mockedEntity = new MockedEntity(1L, "uuid");

        // When
        TableToSyncEnum.getModelClass(mockedEntity);

        // Then
    }

}
