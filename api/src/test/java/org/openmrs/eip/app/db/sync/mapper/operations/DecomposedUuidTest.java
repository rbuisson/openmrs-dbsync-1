package org.openmrs.eip.app.db.sync.mapper.operations;

import org.junit.Test;
import org.openmrs.eip.app.db.sync.entity.light.UserLight;
import org.openmrs.eip.app.db.sync.exception.SyncException;

import static org.junit.Assert.*;

public class DecomposedUuidTest {

    @Test
    public void constructor_should_create_object() {
        // Given
        String userClass = UserLight.class.getName();
        String uuid = "uuid";

        // When
        DecomposedUuid result = new DecomposedUuid(userClass, uuid);

        // Then
        assertEquals(UserLight.class, result.getEntityType());
        assertEquals(uuid, result.getUuid());
    }

    @Test
    public void constructor_should_throw_exception() {
        // Given
        String userClass = "this.class.does.not.Exist";
        String uuid = "uuid";

        try {
            // When
            DecomposedUuid result = new DecomposedUuid(userClass, uuid);

            fail();
        } catch (Exception e) {
            // Then
            assertTrue(e instanceof SyncException);
            assertTrue(e.getCause() instanceof ClassNotFoundException);
            assertEquals("No entity class exists with the name this.class.does.not.Exist", e.getMessage());
        }
    }
}
