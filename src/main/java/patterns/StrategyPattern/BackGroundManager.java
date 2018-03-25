package patterns.StrategyPattern;

/**
 * Created by Nano.son on 2018. 3. 25.
 */
public class BackGroundManager {
    private final Runnable task;
    private DoSomething before_task;
    private DoSomething after_task;

    private BackGroundManager(Runnable task) {
        this.task = task;
        before_task = null;
        after_task = null;
    }

    static class Builder {
        private BackGroundManager backGroundManager;

        Builder (Runnable task) {
            if(task == null)
                throw new RuntimeException("Error, task is null !!");
            backGroundManager = new BackGroundManager(task);
        }

        public Builder beforeTask(DoSomething before_task) {
            this.backGroundManager.setBefore_task(before_task);
            return this;
        }

        public Builder afterTask(DoSomething after_task) {
            this.backGroundManager.setAfter_task(after_task);
            return this;
        }

        public BackGroundManager build() {
            return this.backGroundManager;
        }
    }

    public void run() {
        if(before_task != null)
            before_task.doSomething();
        task.run();
        if(after_task != null)
            after_task.doSomething();
    }

    private void setBefore_task(DoSomething before_task) {
        this.before_task = before_task;
    }

    private void setAfter_task(DoSomething after_task) {
        this.after_task = after_task;
    }
}
