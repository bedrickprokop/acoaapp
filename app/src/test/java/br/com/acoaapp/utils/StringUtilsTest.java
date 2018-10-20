package br.com.acoaapp.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringUtilsTest {

    @Test
    public void testCapEachWord() {
        String str = StringUtils.capEachWord("teste");
        Assert.assertEquals("Teste", str);
    }

    @Test
    public void testCapEachWordWithNullValue() {
        String str = StringUtils.capEachWord(null);
        Assert.assertEquals("", str);
    }

    @Test
    public void testGetInitials() {
        String initials = StringUtils.getInitials("nome");
        Assert.assertEquals("N", initials);
    }

    @Test
    public void testGetInitialsWithNullValue() {
        String initials = StringUtils.getInitials(null);
        Assert.assertEquals("", initials);
    }

    @Test
    public void testContainsIgnoreCase() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Nome");
        boolean isContained = StringUtils.containsIgnoreCase(stringList, "meu nome é arduino");
        Assert.assertTrue(isContained);
    }
}
