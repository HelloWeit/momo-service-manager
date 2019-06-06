package cn.weit.happymo.other;

import org.n3r.idworker.IdWorker;
import org.n3r.idworker.WorkerIdStrategy;
import org.n3r.idworker.strategy.DefaultWorkerIdStrategy;
import org.n3r.idworker.utils.Utils;
import org.springframework.stereotype.Component;

/**
 * @author weitong
 */
@Component
public class MoId {
    private static WorkerIdStrategy workerIdStrategy;
    private static IdWorker idWorker;

    static {
        configure();
    }


    private static synchronized void configure() {
        if (workerIdStrategy != null) workerIdStrategy.release();
        workerIdStrategy = DefaultWorkerIdStrategy.instance;
        workerIdStrategy.initialize();
        idWorker = new IdWorker(workerIdStrategy.availableWorkerId()) {
            @Override
            public long getEpoch() {
                return Utils.midnightMillis();
            }
        };
    }

    public long next() {
        return idWorker.nextId();
    }

    public long getWorkerId() {
        return idWorker.getWorkerId();
    }


}
