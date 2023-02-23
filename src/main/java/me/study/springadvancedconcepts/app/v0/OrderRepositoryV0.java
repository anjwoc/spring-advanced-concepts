package me.study.springadvancedconcepts.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        // Saved Logic
        if (itemId.equals("ex")) {
            throw new IllegalStateException("Exception 발생");
        }

        sleep(1000);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
