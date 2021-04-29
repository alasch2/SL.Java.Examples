package io.sealights.onpremise.slmock.agent.logic;

import io.sealights.onpremise.slmock.agent.dto.MethodInfoDTO;
import io.sealights.onpremise.slmock.agent.instrument.ApiRepository;

import java.util.Date;
import java.util.TimerTask;

public class FileDetectionTask  extends TimerTask {

    int count = 1;

    // run is a abstract method that defines task performed at scheduled time.
    public void run() {
        System.out.println("Task performed on: " + new Date() + "n" +
                "Thread's name: " + Thread.currentThread().getName());

        RedfineBl redfineBl = new RedfineBl();
        MethodInfoDTO methodInfoDTO = ApiRepository.getMethodInfoDTO("/", "GET");
        if (methodInfoDTO != null)
        {
            redfineBl.redfineClass(methodInfoDTO.getClassName(), methodInfoDTO.getClassName());
        }

    }
}
