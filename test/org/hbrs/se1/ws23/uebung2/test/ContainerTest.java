package org.hbrs.se1.ws23.uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    Container container;
    @BeforeEach
    void setUp() {
        container = new Container();
    }

    @Test
    void addMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
        assertThrows(ContainerException.class, ()-> container.addMember(member1)); //testing for duplicates
        assertThrows(ContainerException.class, ()-> container.addMember(member1));
        assertThrows(Exception.class, ()->container.addMember(null));
    }

    @Test
    void deleteMember() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        container.addMember(member1);
        container.addMember(member2);
        assertEquals("Das Member-Objekt mit der ID " + 1 + " ist gelöscht", container.deleteMember(1));
        assertEquals("Das Member-Objekt mit der ID " + 2 + " ist gelöscht", container.deleteMember(2));
        assertEquals("Das Member-Objekt mit der ID " + 2 + " existiert nicht!", container.deleteMember(2));

    }

    @Test
    void size() throws ContainerException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
        container.deleteMember(1);
        assertEquals(1, container.size());

    }
}