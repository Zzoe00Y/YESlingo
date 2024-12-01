package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CommonUserFactory class.
 * These tests ensure the factory correctly creates CommonUser objects
 * with the provided parameters.
 */
class CommonUserFactoryTest {

    /**
     * Tests the create method of the CommonUserFactory class.
     * Verifies that the created object is a CommonUser instance and that
     * its attributes are set correctly based on the input parameters.
     */
    @Test
    void testCreate() {
        CommonUserFactory factory = new CommonUserFactory();

        String name = "testUser";
        String password = "testPass";

        User user = factory.create(name, password);

        assertNotNull(user, "The created user should not be null.");
        assertInstanceOf(CommonUser.class, user, "The created user should be an instance of CommonUser.");
        assertEquals(name, user.getName(), "The name of the created user should match the input.");
        assertEquals(password, user.getPassword(), "The password of the created user should match the input.");
    }
}
