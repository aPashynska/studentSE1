package org.hbrs.se1.ws23.uebung4.prototype;

import org.hbrs.se1.ws23.solutions.uebung3.Member;
import org.hbrs.se1.ws23.solutions.uebung3.MemberKonkret;
import org.hbrs.se1.ws23.solutions.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.solutions.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container container;

    @BeforeEach
    void setUp() {
        container = Container.getInstance();
        container.getCurrentList().clear();
    }

    @Test
    void testAddUserStory() throws ContainerException {
        assertEquals(0, container.size());
        UserStory story = new UserStory(1, "Titel", 5, 5, 5, 5);
        container.addUserStory(story);
        assertEquals(1, container.size());
    }

    @Test
    void testAddUserStoryWithDuplicateID() throws ContainerException {
        UserStory story1 = new UserStory(1, "Titel", 5, 5, 5, 5);
        UserStory story2 = new UserStory(1, "Titel", 5, 5, 5, 5);
        container.addUserStory(story1);
        Exception exception = assertThrows(ContainerException.class, () -> container.addUserStory(story2));
        assertEquals("ID bereits vorhanden!", exception.getMessage());
    }

    void testSize() throws ContainerException {
        assertEquals(0, container.size());
        container.addUserStory(new UserStory(1, "Titel", 5, 5, 5, 5));
        assertEquals(1, container.size());
    }

}