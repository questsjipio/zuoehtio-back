package com.zuoehtio.serviceutil;

import com.zuoehtio.entity.Progress;
import com.zuoehtio.message.AppException;

import java.util.List;
import java.util.Optional;

public class ServiceHelper {
    public static Progress getCurrentProgress(List<Progress> progresses) {
        Optional<Long> currentProgressId = progresses.stream().map(Progress::getProgressId).max(Long::compare);

        if (currentProgressId.isPresent()) {
            return progresses.stream()
                    .filter(progress -> Long.compare(progress.getProgressId(), currentProgressId.get()) == 0)
                    .toList().get(0);
        }

        throw new AppException();
    }

    private ServiceHelper() {
        // Empty
    }
}