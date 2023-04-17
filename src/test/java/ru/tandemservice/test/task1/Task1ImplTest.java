package ru.tandemservice.test.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Task1ImplTest {

    private final IStringRowsListSorter sorter = Task1Impl.getInstance();
    private final List<String[]> selectDatabase = new ArrayList<>();
    List<String[]> expectedResult = new ArrayList<>();

    @BeforeEach
    void fillingTestData() {
        String[] str1 = {"aba", "1a23", "abc"};
        String[] str2 = {"abaa", "1a22", "abc123bc"};
        String[] str3 = {"abb", "123", "aaaa"};
        String[] str4 = {"aaa3434awer3a", "121", null};
        String[] str5 = {null, "121", "abc"};
        String[] str6 = {"aaa", null, ""};
        String[] str7 = {"", "123", "aba"};

        selectDatabase.add(str1);
        selectDatabase.add(str2);
        selectDatabase.add(str3);
        selectDatabase.add(str4);
        selectDatabase.add(str5);
        selectDatabase.add(str6);
        selectDatabase.add(str7);
    }

    @AfterEach
    void databaseCleanup() {
        selectDatabase.clear();
        expectedResult.clear();
    }

    @Test
    void sortByFirstColumn() {
        String[] str1 = {null, "121", "abc"};
        String[] str2 = {"", "123", "aba"};
        String[] str3 = {"aaa", null, ""};
        String[] str4 = {"aaa3434awer3a", "121", null};
        String[] str5 = {"aba", "1a23", "abc"};
        String[] str6 = {"abb", "123", "aaaa"};
        String[] str7 = {"abaa", "1a22", "abc123bc"};

        expectedResult.add(str1);
        expectedResult.add(str2);
        expectedResult.add(str3);
        expectedResult.add(str4);
        expectedResult.add(str5);
        expectedResult.add(str6);
        expectedResult.add(str7);

        sorter.sort(selectDatabase, 0);

        for (int i = 0; i < expectedResult.size(); i++) {
            assertArrayEquals(expectedResult.get(i), selectDatabase.get(i));
        }
    }

    @Test
    void sortBySecondColumn() {
        String[] str1 = {"aaa", null, ""};
        String[] str2 = {"abaa", "1a22", "abc123bc"};
        String[] str3 = {"aba", "1a23", "abc"};
        String[] str4 = {"aaa3434awer3a", "121", null};
        String[] str5 = {null, "121", "abc"};
        String[] str6 = {"abb", "123", "aaaa"};
        String[] str7 = {"", "123", "aba"};

        expectedResult.add(str1);
        expectedResult.add(str2);
        expectedResult.add(str3);
        expectedResult.add(str4);
        expectedResult.add(str5);
        expectedResult.add(str6);
        expectedResult.add(str7);

        sorter.sort(selectDatabase, 1);

        for (int i = 0; i < expectedResult.size(); i++) {
            assertArrayEquals(expectedResult.get(i), selectDatabase.get(i));
        }
    }

    @Test
    void sortByThirdColumn() {
        String[] str1 = {"aaa3434awer3a", "121", null};
        String[] str2 = {"aaa", null, ""};
        String[] str3 = {"", "123", "aba"};
        String[] str4 = {"aba", "1a23", "abc"};
        String[] str5 = {null, "121", "abc"};
        String[] str6 = {"abaa", "1a22", "abc123bc"};
        String[] str7 = {"abb", "123", "aaaa"};

        expectedResult.add(str1);
        expectedResult.add(str2);
        expectedResult.add(str3);
        expectedResult.add(str4);
        expectedResult.add(str5);
        expectedResult.add(str6);
        expectedResult.add(str7);

        sorter.sort(selectDatabase, 2);

        for (int i = 0; i < expectedResult.size(); i++) {
            assertArrayEquals(expectedResult.get(i), selectDatabase.get(i));
        }
    }
}