package ru.tandemservice.test.task1;

import java.util.List;

/**
 * <h1>Задание №1</h1>
 * Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>Мы будем обращать внимание в первую очередь на структуру кода и владение стандартными средствами java.</p>
 */
public class Task1Impl implements IStringRowsListSorter {

    // ваша реализация должна работать, как singleton. даже при использовании из нескольких потоков.
    private static final IStringRowsListSorter INSTANCE = new Task1Impl();

    private Task1Impl() {
    }

    public static IStringRowsListSorter getInstance() {
        return INSTANCE;
    }

    @Override
    public synchronized void sort(final List<String[]> rows, final int columnIndex) {
        rows.sort((o1, o2) -> compareWithColumn(o1, o2, columnIndex));
    }

    private int compareWithColumn(String[] firstRow, String[] secondRow, int columnIndex) {

        final String regex = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";

        String firstSubString = firstRow[columnIndex];
        String secondSubString = secondRow[columnIndex];

        if ((firstSubString == null && secondSubString == null)) return 0;
        if (firstSubString == null) return -1;
        if (secondSubString == null) return 1;

        if (firstSubString.equals(secondSubString)) return 0;

        String[] substringsOfFirstSubString = firstSubString.split(regex);
        String[] substringsOfSecondSubString = secondSubString.split(regex);

        int counter = substringsOfFirstSubString.length;
        if (counter < substringsOfSecondSubString.length) counter = substringsOfSecondSubString.length;

        for (int i = 0; i < counter; i++) {

            try {
                String subsubOne = substringsOfFirstSubString[i];
                String subsubTwo = substringsOfSecondSubString[i];

                if (!subsubOne.equals(subsubTwo)) {
                    try {
                        int firInt = Integer.parseInt(subsubOne);
                        int secInt = Integer.parseInt(subsubTwo);

                        if (firInt < secInt) return -1;
                        else return 1;
                    } catch (NumberFormatException e) {
                        if (subsubOne.length() > subsubTwo.length()) return 1;
                        if (subsubOne.length() < subsubTwo.length()) return -1;

                        return subsubOne.compareTo(subsubTwo);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return substringsOfFirstSubString.length - substringsOfSecondSubString.length;
            }
        }

        return 0;
    }
}
