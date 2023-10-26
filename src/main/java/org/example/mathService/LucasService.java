package org.example.mathService;

import java.util.ArrayList;
import java.util.List;

public class LucasService {


    public static String listSequence(Integer num) {
        List<Integer> sequence = new ArrayList<>();
        for(int i = 0; i < num + 1; i++) {
            sequence.add(sequenceLucas(i));
        }
        return sequence.toString();
    }

    private static Integer sequenceLucas(Integer num) {
        if (num == 0) {
            return 2;
        } else if (num == 1) {
            return 1;
        } else if (num >= 2) {
            return sequenceLucas(num - 1) + sequenceLucas(num - 2);
        }
        return 0;
    }
}
