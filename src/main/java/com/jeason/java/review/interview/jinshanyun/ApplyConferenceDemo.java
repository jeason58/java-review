package com.jeason.java.review.interview.jinshanyun;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: jeason
 * @Date: 2018/10/23 23:56
 * @Description:
 * 申请会议室：公司有N个会议室，每个会议室的开放时间为 a~b，每个申请者可申请每个会议室在开放时间段内的任意时间段m~n
 */
public class ApplyConferenceDemo {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date dateS = new Date();
        Date dateE = new Date();
        dateS.setHours(8);
        dateE.setHours(20);
        long openTime = dateS.getTime();
        long closeTime = dateE.getTime();
        ConferenceRoom roomA = new ConferenceRoom(1, openTime, closeTime);

        CountDownLatch latch = new CountDownLatch(10);
        for (int i=0; i<100; i++) {
            new Thread(() -> {
                Random random = new Random();
                try {
                    long start=0L, end=0L;
                    while (!(start >= roomA.openTime && end <= roomA.closeTime)) {
                        int hourS = random.nextInt(12) + 8;
                        int hourE = hourS + 1;
                        dateS.setHours(hourS);
                        dateE.setHours(hourE);
                        start = dateS.getTime();
                        end = dateE.getTime();
                    }
                    String isOK = roomA.apply(start, end)? "succeed" : "failed";
                    System.out.printf("%s apply %s ~ %s for roomA: %s\n\n",
                            Thread.currentThread().getName(), sdf.format(dateS), sdf.format(dateE), isOK);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
            System.out.println("over！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/** 会议室类 */
class ConferenceRoom {
    private static final String SEGMENT_START = "start";
    private static final String SEGMENT_END = "end";
    int roomId;
    long openTime;
    long closeTime;
    private List<HashMap<String, Long>> appliedSegmentList;
    private ReentrantLock lock = new ReentrantLock(true);

    public ConferenceRoom(int id, long openTime, long closeTime) {
        this.roomId = id;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.appliedSegmentList = new ArrayList<>();
    }

    public boolean apply(long startTime, long endTime) {
        try {
            lock.lock();
            for (HashMap<String, Long> item: appliedSegmentList) {
                if ( (item.get(SEGMENT_START) >= startTime && item.get(SEGMENT_START) <= endTime) ||
                        (item.get(SEGMENT_END) >= startTime && item.get(SEGMENT_END) <= endTime) ) {
                    return false;
                }
            }
            // apply succeed, and put current segment to appliedSegmentList
            HashMap<String, Long> appliedSegment = new HashMap<>();
            appliedSegment.put(SEGMENT_START, startTime);
            appliedSegment.put(SEGMENT_END, endTime);
            appliedSegmentList.add(appliedSegment);
            return true;
        } finally {
            lock.unlock();
        }
    }
}
