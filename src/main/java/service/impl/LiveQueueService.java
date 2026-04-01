package service.impl;

import entity.Patient;

import java.util.LinkedList;
import java.util.Queue;

public class LiveQueueService {
    private final Queue<Patient> queue;

    public LiveQueueService() {
        this.queue = new LinkedList<>();
    }

    public void registerPatient(Patient patient) {
        queue.offer(patient);
    }

    public Patient callNextPatient() {
        Patient nextPatient = queue.poll();

        if (nextPatient != null) {
            return nextPatient;
        }

        throw new IllegalStateException("Черга порожня, пацієнтів немає!");
    }

    public int getWaitingCount() {
        return queue.size();
    }
}
