package com.mk.techsolutions;

import com.google.common.collect.ImmutableSet;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by kvintus on 12/1/14.
 */
public class TransformOneWordToAnotherTest {
    private TransformOneWordToAnother transformer = new TransformOneWordToAnother();
    private static Set<String> dictionary;

    @BeforeClass
    public static void createDictionary() {
        dictionary = ImmutableSet.of("DAMP",
                "LAMP",
                "LIMP",
                "LIME",
                "LIKE");
    }

    @Test(expected=IllegalArgumentException.class)
    public void startWordIsNullThrowsIllegalArgumentException() {
        transformer.transform(null, "LIKE", dictionary);
    }

    @Test(expected=IllegalArgumentException.class)
    public void stopWordIsNullThrowsIllegalArgumentException() {
        transformer.transform("DAMP", null, dictionary);
    }

    @Test(expected=IllegalArgumentException.class)
    public void startWordIsNotTheSameLengthAsStopWordThrowsIllegalArgumentException() {
        transformer.transform("DAMP", "DAMPER", dictionary);
    }

    @Test(expected=IllegalArgumentException.class)
    public void dictionaryIsNullThrowsIllegalArgumentException() {
        transformer.transform("DAMP", "LIKE", null);
    }

    @Test
    public void dampToLikeReturnsCorrectSet() {
        LinkedList<String> transforms = transformer.transform("DAMP", "LIKE", dictionary);
        assertEquals("First word is not correct", "DAMP", transforms.getFirst());
        assertEquals("Last word is not correct", "LIKE", transforms.getLast());
        assertEquals("Number of steps is not correct", 5, transforms.size());

    }

    @Test
    public void dampToLookReturnsEmptySet() {
        LinkedList<String> transforms = transformer.transform("DAMP", "LOOK", dictionary);
        assertEquals("Number of steps is not correct", 0, transforms.size());

    }
}
