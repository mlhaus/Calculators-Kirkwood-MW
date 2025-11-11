package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetTest {
    private Set s1 = new Set();
    private Set s2 =  new Set();

    @BeforeEach
    void setUp() {
        s1.setElements(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "a", "b", "c")));
        s1.setName("mySet");
        s2.setElements(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "b", "c", "d")));
    }

    @Test
    void getName() {
        // Arrange
        // Arranged in test set up
        // Act
        String nameOfSet = "mySet";
        // Assert
        assertEquals(nameOfSet, s1.getName());
    }

    @Test
    void setNameWithNoInout() {
        assertThrows(IllegalArgumentException.class, () -> s1.setName(""), " Name cannot be empty");
    }

    @Test
    void getElements() {
        // Arrange
        // Arranged in test set up
        // Act
        ArrayList<String> actual = s1.getElements();
        // Assert
        assertEquals(actual, s1.getElements());
    }

    @Test
    void setElements() {
        // Arrange
        Set s3 = new Set();
        // Act
        s3.setElements(new ArrayList<>(Arrays.asList("a", "b", "c")));
        // Assert
        assertEquals( new ArrayList<>(Arrays.asList("a", "b", "c")), s3.getElements());
    }

    @Test
    void addElement() {
        //Arrange
        Set s3 = new Set();
        // Act
        s3.addElement("dog");
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("dog")), s3.getElements());
    }


    @Test
    void removeElement() {
        // Arrange
        // test set up
        // Act
        s2.removeElement("b");
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "c", "d")), s2.getElements());
    }

    @Test
    void union() {
        // Arranged in test set up
        // Act
        Set s3 = s1.union(s2);
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8","a", "b", "c", "d")), s3.getElements());
    }

    @Test
    void unionWithEmptySet() {
        // Arrange
        s1 = new Set();
        // Act
        Set s3 = s1.union(s2);
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "b", "c", "d")), s3.getElements());
    }

    @Test
    void unionWithTwin() {
        // Arrange
        s1.setElements(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "b", "c", "d")));
        // Act
        Set s3 = s1.union(s2);
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "b", "c", "d")), s3.getElements());
    }

    @Test
    void intersection() {
        // Arranged in test set up

        // Act
        Set s3 = s1.intersection(s2);

        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("4", "5","b", "c")), s3.getElements());
    }

    @Test
    void intersectionWithEmptySet() {
        // Arrange
        s1 = new Set();
        // Act
        Set s3 = s1.intersection(s2);
        // Assert
        assertEquals(new ArrayList<>(), s3.getElements());
    }

    @Test
    void intersectionWithTwin() {
        // Arrange
        s1.setElements(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "b", "c", "d")));
        // Act
        Set s3 = s1.intersection(s2);
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("4", "5", "6", "7", "8", "b", "c", "d")), s3.getElements());
    }

    @Test
    void difference() {
        // Arranged in test set up
        // Act
        Set s3 = s1.difference(s2);
        //Assert
        assertEquals(new ArrayList<>(Arrays.asList("1", "2", "3", "a")), s3.getElements());
    }

    @Test
    void differenceWithEmptySetFirst() {
        // Arrange
        s1 = new Set();
        // Act
        Set s3 = s1.difference(s2);
        // Assert
        assertEquals(new ArrayList<>(), s3.getElements());
    }

    @Test
    void differenceWithEmptySetSecond() {
        // Arrange
        s2 = new Set();
        // Act
        Set s3 = s1.difference(s2);
        // Assert
        assertEquals(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "a", "b", "c")), s3.getElements());
    }

    @Test
    void testToString() {
        // Arrange
        // test set up
        // Act
        String expected = "mySet: [1, 2, 3, 4, 5, a, b, c]";
        String actual = s1.toString();
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void sort() {
        // Arrange
        List<String> nums = new ArrayList<>(Arrays.asList("4", "5", "6", "7", "9", "a", "c", "f"));
        Set s3 = new Set("sortSet", new ArrayList<>(Arrays.asList("5", "6", "7", "4", "9", "a", "f", "c")));
        // Act
        s3.sort();
        // Assert
        assertEquals(nums, s3.getElements());
    }

}